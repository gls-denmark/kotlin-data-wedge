package dk.gls.kdw.configuration

import android.os.Bundle
import dk.gls.kdw.configuration.model.ConfigMode
import dk.gls.kdw.configuration.plugin.PluginConfiguration
import dk.gls.kdw.configuration.plugin.toBundle

/**
 * @see <a href="https://techdocs.zebra.com/datawedge/latest/guide/api/setconfig/">Official documentation </a>
 **/
class ProfileConfiguration(
    val enabled: Boolean = true,
    val name: String,
    val configMode: ConfigMode,
    val pluginConfigurations: ArrayList<PluginConfiguration>,
    val appList: ArrayList<AppConfiguration>
)

fun ProfileConfiguration.toBundle(): Bundle {
    return Bundle().apply {
        this@apply.putBoolean("PROFILE_ENABLED", enabled)
        this@apply.putString("PROFILE_NAME", name)
        this@apply.putString("CONFIG_MODE", configMode.toString())

        val pluginBundles = arrayListOf(*pluginConfigurations
            .map {
                it.toBundle()
            }
            .toTypedArray()
        )
        this@apply.putParcelableArrayList("PLUGIN_CONFIG", pluginBundles)

        val appBundles = arrayOf(*appList
            .map {
                it.toBundle()
            }
            .toTypedArray()
        )
        this@apply.putParcelableArray("APP_LIST", appBundles)
    }
}