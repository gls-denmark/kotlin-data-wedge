package dk.gls.kdw.configuration

import android.os.Bundle
import dk.gls.kdw.configuration.model.ConfigMode
import dk.gls.kdw.configuration.plugin.PluginConfiguration
import dk.gls.kdw.configuration.plugin.toBundle

/**
 * The profile configuration defines the profile name and config mode and contains a list of the [PluginConfiguration] and [AppConfiguration] determining what to configure and which apps the configuration should apply to
 *
 * @param [enabled] whether the profile should be enabled or not
 * @param [name] what name the profile should be listed under in the "DataWedge" apps profiles list
 * @param [configMode] which config mode the profile should be applied with. Default is [ConfigMode.OVERWRITE]
 * @param [pluginConfigurations] the plugins that should be configured in the profile
 * @param [appList] a list of the app's packageName and activityList of the activities that the profile should be enabled in, in the given app
 * @see <a href="https://techdocs.zebra.com/datawedge/latest/guide/api/setconfig/">Official documentation </a>
 **/
class ProfileConfiguration(
    val enabled: Boolean = true,
    val name: String,
    val configMode: ConfigMode = ConfigMode.OVERWRITE,
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