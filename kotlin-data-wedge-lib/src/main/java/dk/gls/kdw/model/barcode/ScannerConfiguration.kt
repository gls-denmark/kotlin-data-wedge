package dk.gls.kdw.model.barcode

import android.os.Bundle
import dev.ahmedmourad.bundlizer.Bundlizer
import dk.gls.kdw.model.barcode.scanner.ScannerIdentifier
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ScannerConfiguration(
    @SerialName("scanner_selection")
    val scannerSelection: ScannerIdentifier = ScannerIdentifier.AUTO,
    @SerialName("scanner_input_enabled")
    val inputEnabled: Boolean = true
)

fun ScannerConfiguration.toBundle(): Bundle = Bundlizer.bundle(ScannerConfiguration.serializer(), this)