package dk.gls.kdw.model.scanner

sealed interface ScannerOutput {
    class Result(val scannerResult: ScannerResult) : ScannerOutput
    class Status(val scannerStatus: ScannerStatus) : ScannerOutput
}