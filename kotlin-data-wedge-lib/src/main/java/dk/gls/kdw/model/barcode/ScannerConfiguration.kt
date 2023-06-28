package dk.gls.kdw.model.barcode

import android.os.Bundle
import dk.gls.kdw.model.barcode.scanner.ScannerIdentifier

data class ScannerConfiguration(
    val scannerSelection: ScannerIdentifier = ScannerIdentifier.AUTO,
    val inputEnabled: Boolean = true
)

fun ScannerConfiguration.toBundle(): Bundle {
    return Bundle().apply {
        this.putString("scanner_selection", scannerSelection.toString())
        this.putBoolean("scanner_input_enabled", inputEnabled)
    }
}