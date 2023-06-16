package dk.gls.kdw.model.scanner

/**
 * The scanner output, being either a [Result] or a [Status]
 */
sealed interface ScannerOutput {
    /**
     * When the scanner has scanned a barcode that is enabled
     */
    class Result(val scannerResult: ScannerResult) : ScannerOutput

    /**
     * When the scanner changes status, eg. being turned on or off by pushing or releasing hardware buttons
     */
    class Status(val scannerStatus: ScannerStatus) : ScannerOutput
}