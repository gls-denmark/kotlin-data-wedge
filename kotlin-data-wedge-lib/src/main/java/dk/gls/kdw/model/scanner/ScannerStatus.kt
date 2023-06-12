package dk.gls.kdw.model.scanner

sealed interface ScannerStatus {
    object Enabled : ScannerStatus
    object Disabled : ScannerStatus
}