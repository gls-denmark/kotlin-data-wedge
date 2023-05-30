package dk.gls.kdw.configuration.plugin

import android.os.Bundle
import dk.gls.kdw.configuration.model.Plugin

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