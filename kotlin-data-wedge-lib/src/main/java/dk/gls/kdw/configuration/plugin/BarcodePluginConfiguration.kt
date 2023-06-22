package dk.gls.kdw.configuration.plugin

import dk.gls.kdw.configuration.model.Plugin
import dk.gls.kdw.model.barcode.BarcodeConfiguration

/**
 * The BARCODE plugin
 * @param [barcodeConfiguration] define the barcode configuration, configuring individual format settings
 * @param [configModeReset] see [PluginConfiguration.configModeReset]
 */
data class BarcodePluginConfiguration(
    val barcodeConfiguration: BarcodeConfiguration,
    override val configModeReset: Boolean = true
) : PluginConfiguration(Plugin.BARCODE, barcodeConfiguration.toBundle(), configModeReset)