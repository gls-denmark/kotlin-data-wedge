package dk.gls.kdw.configuration.scanner

import dk.gls.kdw.model.scanner.ScannerResult

sealed interface ScannerOutput {
    class Result(val scannerResult: ScannerResult) : ScannerOutput
    class Status(val scannerStatus: ScannerStatus) : ScannerOutput
}