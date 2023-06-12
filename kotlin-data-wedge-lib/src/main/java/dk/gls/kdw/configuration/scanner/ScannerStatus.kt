package dk.gls.kdw.configuration.scanner

enum class ScannerStatus(val value: String) {
    WAITING("WAITING"),
    SCANNING("SCANNING"),
    CONNECTED("CONNECTED"),
    DISCONNECTED("DISCONNECTED"),
    IDLE("IDLE"),
    DISABLED("DISABLED")
}

fun String.toScannerStatusEnum(): ScannerStatus {
    return ScannerStatus.valueOf(this)
}