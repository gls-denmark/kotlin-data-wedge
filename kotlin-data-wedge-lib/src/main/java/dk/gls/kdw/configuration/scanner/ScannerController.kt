package dk.gls.kdw.configuration.scanner

import dk.gls.kdw.model.scanner.ScannerOutput
import kotlinx.coroutines.flow.Flow

/**
 * The scanner controller makes it possible to suspend and resume the scanner and monitor [ScannerOutput] using a Flow
 */
abstract class ScannerController(
    val dataWedgeHardwareScanner: DataWedgeHardwareScanner
) {

    abstract fun scannerOutputFlow(): Flow<ScannerOutput>

    abstract fun suspendScanner()

    abstract fun resumeScanner()

}