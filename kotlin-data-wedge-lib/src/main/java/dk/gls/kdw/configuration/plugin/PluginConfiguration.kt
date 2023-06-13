package dk.gls.kdw.configuration.plugin

import android.os.Bundle
import dk.gls.kdw.configuration.model.Plugin

/**
 * The plugin to be configured
 * @param [pluginName] the name of the plugin to configure
 * @param [paramList] a bundle of the settings that should be applied
 * @param [configModeReset] applies to an existing Profile with CONFIG_MODE CREATE_IF_NOT_EXIST or UPDATE. True (Default) – Clear any existing configuration and create a new configuration with the specified parameter values. False – Merge existing configuration with changes from the new configuration - update the existing values and add values not already in the configuration
 */
abstract class PluginConfiguration(
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