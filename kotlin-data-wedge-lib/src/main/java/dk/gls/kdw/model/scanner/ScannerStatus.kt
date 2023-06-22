package dk.gls.kdw.model.scanner

import dk.gls.kdw.model.label.LabelType

/**
 * Scanner status indicates that the scanner is being turned on or off using the hardware buttons.
 * @see <a href="https://techdocs.zebra.com/datawedge/13-0/guide/programmers-guides/dw-programming/">For official documentation </a> under 'Data Capture'
 */
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