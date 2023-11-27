package dk.gls.kdw.configuration.scanner

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import dk.gls.kdw.model.scanner.ScannerOutput
import dk.gls.kdw.model.scanner.ScannerResult
import dk.gls.kdw.model.scanner.toScannerStatusEnum
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch


class DataWedgeHardwareScanner(
    private val context: Context,
    dispatcher: CoroutineDispatcher = Dispatchers.IO
) : HardwareScanner {

    companion object {
        //Scanner Intent data locations
        private const val EXTRA_DATA_STRING = "com.symbol.datawedge.data_string"
        private const val EXTRA_LABEL_TYPE = "com.symbol.datawedge.label_type"

        // DataWedge Extras
        private const val EXTRA_KEY_APPLICATION_NAME = "com.symbol.datawedge.api.APPLICATION_NAME"
        private const val EXTRA_KEY_NOTIFICATION_TYPE = "com.symbol.datawedge.api.NOTIFICATION_TYPE"
        private const val EXTRA_RESULT_NOTIFICATION = "com.symbol.datawedge.api.NOTIFICATION"
        private const val EXTRA_REGISTER_NOTIFICATION = "com.symbol.datawedge.api.REGISTER_FOR_NOTIFICATION"


        private const val EXTRA_RESULT_NOTIFICATION_TYPE = "NOTIFICATION_TYPE"
        private const val EXTRA_KEY_VALUE_SCANNER_STATUS = "SCANNER_STATUS"
        private const val EXTRA_KEY_VALUE_PROFILE_SWITCH = "PROFILE_SWITCH"
        private const val EXTRA_KEY_VALUE_NOTIFICATION_STATUS = "STATUS"
        private const val EXTRA_KEY_VALUE_NOTIFICATION_PROFILE_NAME = "PROFILE_NAME"

        // DataWedge Actions
        private const val ACTION_DATA_WEDGE = "com.symbol.datawedge.api.ACTION"
        private const val EXTRA_SCANNER_INPUT_PLUGIN = "com.symbol.datawedge.api.SCANNER_INPUT_PLUGIN"

        private const val NOTIFICATION_ACTION = "com.symbol.datawedge.api.NOTIFICATION_ACTION"
        private const val RESULT_ACTION = "com.symbol.datawedge.api.RESULT_ACTION"

        // DataWedge scanning broadcast
        private const val INTENT_FILTER_ACTION = "com.zebra.datacapture1.ACTION"
        private const val INTENT_FILTER_ACTION_FROM_SERVICE = "com.zebra.datacapture1.service.ACTION"

        // DataWedge control scanner state
        private const val EXTRA_SCANNER_SUSPEND = "SUSPEND_PLUGIN"
        private const val EXTRA_SCANNER_RESUME = "RESUME_PLUGIN"

        // Enumerate scanners
        private const val RESULT_ENUMERATE_SCANNERS = "com.symbol.datawedge.api.RESULT_ENUMERATE_SCANNERS"
        private const val ENUMERATE_SCANNERS = "com.symbol.datawedge.api.ENUMERATE_SCANNERS"

        // Notify
        private const val ACTION_NOTIFICATION_NOTIFY = "com.symbol.datawedge.api.notification.NOTIFY"
        private const val BUNDLE_NOTIFICATION_CONFIG = "NOTIFICATION_CONFIG"
        private const val EXTRA_KEY_VALUE_DEVICE_IDENTIFIER = "DEVICE_IDENTIFIER"
        private const val EXTRA_KEY_VALUE_NOTIFICATION_SETTINGS = "NOTIFICATION_SETTINGS"

    }

    private val scope = CoroutineScope(dispatcher)

    init {
        registerForStatusChange()
        scope.launch {
            subscribeToScannerFlow()
        }
    }

    private val _scannerFlow = MutableSharedFlow<ScannerOutput>()

    //region Scanner controller

    /** Collect [ScannerOutput] from the scanner **/
    override fun scannerOutputFlow(): Flow<ScannerOutput> = _scannerFlow

    /** Suspend the scanner **/
    override fun suspendScanner() {
        broadcastScannerAction(context, EXTRA_SCANNER_SUSPEND)
    }

    /** Resume the scanner **/
    override fun resumeScanner() {
        broadcastScannerAction(context, EXTRA_SCANNER_RESUME)
    }

    /**
     * Send a notification on the remote scanner (RMS)
     * @link https://techdocs.zebra.com/datawedge/8-2/guide/api/notify/
     **/
    override fun remoteScannerNotification(deviceId: DeviceId, notifications: List<RemoteScannerNotification>) {
        val bundleNotificationConfig = Bundle()
        bundleNotificationConfig.putString(EXTRA_KEY_VALUE_DEVICE_IDENTIFIER, deviceId.name)
        bundleNotificationConfig.putIntArray(EXTRA_KEY_VALUE_NOTIFICATION_SETTINGS, notifications.map { it.value }.toIntArray())

        val bundleNotify = Bundle()
        bundleNotify.putBundle(BUNDLE_NOTIFICATION_CONFIG, bundleNotificationConfig)


        val dataWedgeIntent = Intent()
        dataWedgeIntent.action = ACTION_DATA_WEDGE
        dataWedgeIntent.putExtra(ACTION_NOTIFICATION_NOTIFY, bundleNotify)

        context.sendBroadcast(dataWedgeIntent)
    }

    //endregion

    /** Register broadcast receiver for receiving barcode scans. Collect the received broadcasts and emit on scannerResult flow **/
    private suspend fun subscribeToScannerFlow() {
        callbackFlow {
            val receiver = object : BroadcastReceiver() {
                override fun onReceive(context: Context?, intent: Intent) {
                    Log.d("ScannerFlow", "${intent.action}, hasExtras: ${intent.hasExtra(RESULT_ENUMERATE_SCANNERS)}")
                    // Received a barcode scan
                    if (intent.action == INTENT_FILTER_ACTION) {
                        val scannerResult = extractDataFromIntent(intent)
                        trySendBlocking(ScannerOutput.Result(scannerResult))
                    }
                    // Received a scanner or profile switch status notification
                    if (intent.action == NOTIFICATION_ACTION) {
                        val extras = intent.getBundleExtra(EXTRA_RESULT_NOTIFICATION)
                        val notificationType = extras?.getString(EXTRA_RESULT_NOTIFICATION_TYPE)
                        when (notificationType) {
                            // The scanner changed status
                            EXTRA_KEY_VALUE_SCANNER_STATUS -> {
                                val scannerStatus = extras.getString(EXTRA_KEY_VALUE_NOTIFICATION_STATUS)

                                scannerStatus?.let {
                                    val statusEnum = it.toScannerStatusEnum()


                                    requestEnumerateScanners()


                                    trySendBlocking(ScannerOutput.Status(statusEnum))
                                }
                            }

                            EXTRA_KEY_VALUE_PROFILE_SWITCH -> {
                                // I am not totally sure when we are interested in this
                                val notificationProfileName = extras.getString(EXTRA_KEY_VALUE_NOTIFICATION_PROFILE_NAME)
                            }
                        }
                    }
                    if (intent.hasExtra(RESULT_ENUMERATE_SCANNERS)) {
                        val scannerList = intent.getSerializableExtra(RESULT_ENUMERATE_SCANNERS) as ArrayList<Bundle>
                        if(scannerList.size > 0) {
                            scannerList.map { bunb ->
                                val name = bunb.getString("SCANNER_NAME")
                                val connectionState = bunb.getBoolean("SCANNER_CONNECTION_STATE")
                                val index = bunb.getInt("SCANNER_INDEX")
                                val id = bunb.getString("SCANNER_IDENTIFIER")

                                Log.d("ScannerList", "Scanner: name: $name connectionState: $connectionState index: $index id: $id")
                            }
                        }
                    }
                }
            }
            context.registerReceiver(receiver, createIntentFilter())
            awaitClose {
                context.unregisterReceiver(receiver)
            }
        }.collect {
            _scannerFlow.emit(it)
        }
    }

    /** Create and send intent filter in order to retrieve enumerate triggers **/
    private fun requestEnumerateScanners() {
        val dataWedgeIntent = Intent()

        dataWedgeIntent.setAction(ACTION_DATA_WEDGE)
        dataWedgeIntent.putExtra(ENUMERATE_SCANNERS, "")

        context.sendBroadcast(dataWedgeIntent)
    }

    /** Create filter for the broadcast intent **/
    private fun createIntentFilter(): IntentFilter {
        val filter = IntentFilter()
        // Register to received broadcasts via Data Wedge scanning
        filter.addAction(RESULT_ACTION)
        // For notification result
        filter.addAction(NOTIFICATION_ACTION)
        // Register to received broadcasts via DataWedge scanning
        filter.addAction(INTENT_FILTER_ACTION)
        filter.addAction(INTENT_FILTER_ACTION_FROM_SERVICE)

        return filter
    }

    /** We register for scanner status changes **/
    private fun registerForStatusChange() {
        val bundle = Bundle()
        bundle.putString(EXTRA_KEY_APPLICATION_NAME, context.packageName)
        bundle.putString(EXTRA_KEY_NOTIFICATION_TYPE, EXTRA_KEY_VALUE_SCANNER_STATUS)

        val dataWedgeIntent = Intent()
        dataWedgeIntent.action = ACTION_DATA_WEDGE
        dataWedgeIntent.putExtra(EXTRA_REGISTER_NOTIFICATION, bundle)

        context.sendBroadcast(dataWedgeIntent)
    }

    /** Retrieves data from received intent **/
    private fun extractDataFromIntent(scanIntent: Intent): ScannerResult {
        val data = scanIntent.getStringExtra(EXTRA_DATA_STRING)
        val symbology = scanIntent.getStringExtra(EXTRA_LABEL_TYPE)

        return ScannerResult(data, symbology)
    }

    private fun broadcastScannerAction(context: Context, action: String) {
        val intent = Intent()
        intent.action = ACTION_DATA_WEDGE
        intent.putExtra(EXTRA_SCANNER_INPUT_PLUGIN, action)
        context.sendBroadcast(intent)
    }

}