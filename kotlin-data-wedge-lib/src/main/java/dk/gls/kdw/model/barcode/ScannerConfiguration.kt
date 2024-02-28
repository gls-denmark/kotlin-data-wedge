package dk.gls.kdw.model.barcode

import android.os.Bundle
import dk.gls.kdw.model.barcode.scanner.ScannerIdentifier
import dk.gls.kdw.model.inputParameters.AutoSwitchToDefaultOnEvent

data class ScannerConfiguration(
    val scannerSelection: ScannerIdentifier = ScannerIdentifier.AUTO,
    /** Specify which scanner the configurations apply to **/
    val configureAllScanners : Boolean = true,
    val inputEnabled: Boolean = true,
    val autoSwitchToDefaultOnEvent: AutoSwitchToDefaultOnEvent = AutoSwitchToDefaultOnEvent.DISABLED,
)

fun ScannerConfiguration.toBundle(): Bundle {
    return Bundle().apply {
        this.putString("scanner_selection", scannerSelection.toString())
        this.putBoolean("scanner_input_enabled", inputEnabled)
        this.putString("configure_all_scanners", configureAllScanners.toString())
        this.putString("auto_switch_to_default_on_event", autoSwitchToDefaultOnEvent.value.toString())
    }
}