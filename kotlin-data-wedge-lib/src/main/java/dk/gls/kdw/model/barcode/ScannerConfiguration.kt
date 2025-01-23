package dk.gls.kdw.model.barcode

import android.os.Bundle
import dk.gls.kdw.model.barcode.scanner.ScannerIdentifier
import dk.gls.kdw.model.inputParameters.AutoSwitchToDefaultOnEvent

/**
 * @param scannerConnectionTimeout idle timeout before scanner disconnects in seconds. 0 to disable. max 1800
 */
data class ScannerConfiguration(
    val scannerSelection: ScannerIdentifier = ScannerIdentifier.AUTO,
    /** Specify which scanner the configurations apply to **/
    val configureAllScanners : Boolean = true,
    val inputEnabled: Boolean = true,
    val autoSwitchToDefaultOnEvent: AutoSwitchToDefaultOnEvent = AutoSwitchToDefaultOnEvent.DISABLED,
    val scannerConnectionTimeout: Int = 600
)

fun ScannerConfiguration.toBundle(): Bundle {
    return Bundle().apply {
        this.putInt("connection_idle_time", scannerConnectionTimeout)
        this.putString("scanner_selection", scannerSelection.toString())
        this.putBoolean("scanner_input_enabled", inputEnabled)
        this.putString("configure_all_scanners", configureAllScanners.toString())
        this.putString("auto_switch_to_default_on_event", autoSwitchToDefaultOnEvent.value.toString())
    }
}