package dk.gls.kdw.model.scanner

/**
 * The scanner output, being either a [Result] or a [Status]
 */
sealed interface ScannerOutput {
    /**
     * The scanner
     */
    class Result(val scannerResult: ScannerResult) : ScannerOutput
    class Status(val scannerStatus: ScannerStatus) : ScannerOutput
}