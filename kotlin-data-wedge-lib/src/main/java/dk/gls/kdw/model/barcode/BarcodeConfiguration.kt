package dk.gls.kdw.model.barcode

import android.os.Bundle

class BarcodeConfiguration(
    val scannerConfiguration: ScannerConfiguration,
    val configurations: List<Bundleable>
) {

    fun toBundle(): Bundle {
        val barcodeConfig = Bundle()
        barcodeConfig.putString("PLUGIN_NAME", "BARCODE")
        barcodeConfig.putString("RESET_CONFIG", "true")

        val barcodeConfigPropertyBundle = configurations
            .plus(scannerConfiguration)
            .fold(Bundle()) { barcodeConfigPropertyBundle, item ->
                barcodeConfigPropertyBundle.addToBundle(item)
                return@fold barcodeConfigPropertyBundle
            }

        barcodeConfig.putBundle("PARAM_LIST", barcodeConfigPropertyBundle)

        return barcodeConfig
    }

}