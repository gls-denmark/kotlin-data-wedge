package dk.gls.kdw.configuration.scanner

import android.util.Log
import dk.gls.kdw.model.scanner.ScannerOutput
import dk.gls.kdw.model.scanner.ScannerStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

/**
 * The parity flow scanner controller filters unnecessary calls to the underlying [DataWedgeHardwareScanner] by checking the last reported scanner status before sending new resume or suspend commands
 * Therefore this scanner controllers [resumeScanner] and [suspendScanner] methods can safely be called multiple times without resulting in unnecessary broadcast intents being send to the Data wedge
 */
class ParityFlowScannerController(dataWedgeHardwareScanner: DataWedgeHardwareScanner) : ScannerController(dataWedgeHardwareScanner) {

    //We have an actual scanner status flow and a desired scanner status flow for combining later
    private val actualScannerStatusEnumFlow = MutableSharedFlow<ScannerSimpleStatus>(replay = 1)
    private val desiredScannerStatusFlow = MutableSharedFlow<ScannerSimpleStatus>(replay = 1)

    init {
        GlobalScope.launch {
            subscribeToScannerFlow()
            scannerStatusParityFlow()
        }
    }

    /**
     * Receive the [ScannerOutput] as a flow
     */
    override fun scannerOutputFlow(): Flow<ScannerOutput> = dataWedgeHardwareScanner.scannerOutputFlow()

    //region Scanner status public interface

    /**
     * Suspend the scanner
     * Only calls suspend on the hardware scanner if the current state is not suspend
     */
    override fun suspendScanner() {
        desiredScannerStatusFlow.tryEmit(ScannerSimpleStatus.Disabled)
    }

    /**
     * Resume the scanner
     * Only calls resume on the hardware scanner if the current state is not resume
     */
    override fun resumeScanner() {
        desiredScannerStatusFlow.tryEmit(ScannerSimpleStatus.Enabled)
    }

    //endregion

    private val scannerResult = MutableSharedFlow<ScannerOutput>()

    //region Scanner status private

    private fun CoroutineScope.subscribeToScannerFlow() = launch {
        scannerOutputFlow().collect {
            if(it is ScannerOutput.Status) {
                actualScannerStatusEnumFlow.emit(it.scannerStatus.toScannerSimpleStatus())
            }
        }
    }

    /** Combine actual and desired flows and toggle the scanner if they are not equal **/
    private fun CoroutineScope.scannerStatusParityFlow() = launch {
        combine(actualScannerStatusEnumFlow, desiredScannerStatusFlow) { actual, desired ->
            Log.d("ParityFlowScannerController", "scannerStatusParityFlow: $actual, $desired")
            if (actual != desired) {
                when (desired) {
                    ScannerSimpleStatus.Disabled -> dataWedgeHardwareScanner.suspendScanner()
                    ScannerSimpleStatus.Enabled -> dataWedgeHardwareScanner.resumeScanner()
                }
            }
        }.collect()
    }
}

/**
 * A convenience interface used to map the different [ScannerStatus] states into the two states we are interested in -> Enabled or Disabled
 */
internal sealed interface ScannerSimpleStatus {
    object Enabled : ScannerSimpleStatus
    object Disabled : ScannerSimpleStatus
}

/**
 * We map the incoming scanner status to the two we are interested in -> Enabled or Disabled
 **/
private fun ScannerStatus.toScannerSimpleStatus(): ScannerSimpleStatus {
    return when (this) {
        ScannerStatus.DISCONNECTED, ScannerStatus.IDLE, ScannerStatus.DISABLED -> {
            ScannerSimpleStatus.Disabled
        }

        ScannerStatus.WAITING, ScannerStatus.SCANNING, ScannerStatus.CONNECTED -> {
            ScannerSimpleStatus.Enabled
        }
    }
}