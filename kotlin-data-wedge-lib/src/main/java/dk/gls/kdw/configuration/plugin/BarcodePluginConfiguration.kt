package dk.gls.kdw.configuration.plugin

import dk.gls.kdw.configuration.model.Plugin
import dk.gls.kdw.model.barcode.BarcodeConfiguration

data class BarcodePluginConfiguration(
    val barcodeConfiguration: BarcodeConfiguration,
    override val configModeReset: Boolean = true
) : PluginConfiguration(Plugin.BARCODE, barcodeConfiguration.toBundle(), configModeReset)