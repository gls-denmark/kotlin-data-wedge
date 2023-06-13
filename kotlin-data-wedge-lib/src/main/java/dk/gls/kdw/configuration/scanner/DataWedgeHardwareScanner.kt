package dk.gls.kdw.configuration.scanner

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
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
        private const val TAG = "ScannerController"

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

        private const val ACTION_RESULT_NOTIFICATION = "com.symbol.datawedge.api.NOTIFICATION_ACTION"
        private const val ACTION_RESULT = "com.symbol.datawedge.api.RESULT_ACTION"

        // DataWedge scanning broadcast
        private const val INTENT_FILTER_ACTION = "com.zebra.datacapture1.ACTION"
        private const val INTENT_FILTER_ACTION_FROM_SERVICE = "com.zebra.datacapture1.service.ACTION"

        // DataWedge control scanner state
        private const val EXTRA_SCANNER_SUSPEND = "SUSPEND_PLUGIN"
        private const val EXTRA_SCANNER_RESUME = "RESUME_PLUGIN"
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

    //endregion

    /** Register broadcast receiver for receiving barcode scans. Collect the received broadcasts and emit on scannerResult flow **/
    private suspend fun subscribeToScannerFlow() {
        callbackFlow {
            val receiver = object : BroadcastReceiver() {
                override fun onReceive(context: Context?, intent: Intent) {
                    // Received a barcode scan
                    if (intent.action == INTENT_FILTER_ACTION) {
                        val scannerResult = extractDataFromIntent(intent)
                        trySendBlocking(ScannerOutput.Result(scannerResult))
                    }
                    // Received a scanner or profile switch status notification
                    if (intent.action == ACTION_RESULT_NOTIFICATION) {
                        val extras = intent.getBundleExtra(EXTRA_RESULT_NOTIFICATION)
                        val notificationType = extras?.getString(EXTRA_RESULT_NOTIFICATION_TYPE)
                        when (notificationType) {
                            // The scanner changed status
                            EXTRA_KEY_VALUE_SCANNER_STATUS -> {
                                val scannerStatus = extras.getString(EXTRA_KEY_VALUE_NOTIFICATION_STATUS)

                                //TODO Add error handling in case scanner status is null?
                                scannerStatus?.let {
                                    val statusEnum = it.toScannerStatusEnum()
                                    trySendBlocking(ScannerOutput.Status(statusEnum))
                                }
                            }

                            EXTRA_KEY_VALUE_PROFILE_SWITCH -> {
                                // I am not totally sure when we are interested in this
                                val notificationProfileName = extras.getString(EXTRA_KEY_VALUE_NOTIFICATION_PROFILE_NAME)
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

    /** Create filter for the broadcast intent **/
    private fun createIntentFilter(): IntentFilter {
        val filter = IntentFilter()
        // Register to received broadcasts via Data Wedge scanning
        filter.addAction(ACTION_RESULT)
        // For notification result
        filter.addAction(ACTION_RESULT_NOTIFICATION)
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