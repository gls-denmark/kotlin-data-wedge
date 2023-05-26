package dk.gls.kdw.configuration

import android.os.Bundle
import dk.gls.kdw.configuration.model.Plugin
import dk.gls.kdw.model.barcode.BarcodeConfiguration

open class PluginConfiguration(
    val pluginName: Plugin,
    val paramList: Bundle,
    open val configModeReset: Boolean = true
)

fun PluginConfiguration.toBundle() : Bundle {
    return Bundle().apply {
        this.putString("PLUGIN_NAME", this@toBundle.pluginName.toString())
        this.putBundle("PARAM_LIST", this@toBundle.paramList)
        this.putBoolean("RESET_CONFIG", this@toBundle.configModeReset)
    }
}

data class BarcodePluginConfiguration(
    val barcodeConfiguration: BarcodeConfiguration,
    override val configModeReset: Boolean = true
) : PluginConfiguration(Plugin.BARCODE, barcodeConfiguration.toBundle(), configModeReset)