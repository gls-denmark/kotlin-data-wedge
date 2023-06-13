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

class ParityFlowScannerController(
    dataWedgeHardwareScanner: DataWedgeHardwareScanner
) : ScannerController(dataWedgeHardwareScanner) {

    //We have an actual scanner status flow and a wished scanner status flow for combining later
    private val actualScannerStatusEnumFlow = MutableSharedFlow<ScannerSimpleStatus>(replay = 1)
    private val wishedScannerStatusFlow = MutableSharedFlow<ScannerSimpleStatus>(replay = 1)

    init {
        GlobalScope.launch {
            subscribeToScannerFlow()
            scannerStatusParityFlow()
        }
    }

    override fun scannerOutputFlow(): Flow<ScannerOutput> = dataWedgeHardwareScanner.scannerOutputFlow()

    //region Scanner status public interface

    override fun suspendScanner() {
        wishedScannerStatusFlow.tryEmit(ScannerSimpleStatus.Disabled)
    }

    override fun resumeScanner() {
        wishedScannerStatusFlow.tryEmit(ScannerSimpleStatus.Enabled)
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

    /** Combine actual and wished flows and toggle the scanner if they are not equal **/
    private fun CoroutineScope.scannerStatusParityFlow() = launch {
        combine(actualScannerStatusEnumFlow, wishedScannerStatusFlow) { actual, wished ->
            Log.d("ParityFlowScannerController", "scannerStatusParityFlow: $actual, $wished")
            if (actual != wished) {
                when (wished) {
                    ScannerSimpleStatus.Disabled -> dataWedgeHardwareScanner.suspendScanner()
                    ScannerSimpleStatus.Enabled -> dataWedgeHardwareScanner.resumeScanner()
                }
            }
        }.collect()
    }
}

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