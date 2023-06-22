package dk.gls.kdw.configuration.plugin

import dk.gls.kdw.configuration.model.Plugin
import dk.gls.kdw.model.intent.SubscriptionConfiguration
import dk.gls.kdw.model.intent.toBundle

/**
 * The INTENT plugin
 * @param [subscriptionConfiguration] define the how data is received, should not be changed from default if you wish to use the [ScannerController] interface.
 * @param [configModeReset] see [PluginConfiguration.configModeReset]
 */
data class IntentPluginConfiguration(
    val subscriptionConfiguration: SubscriptionConfiguration = SubscriptionConfiguration(),
    override val configModeReset: Boolean = true
) : PluginConfiguration(Plugin.INTENT, subscriptionConfiguration.toBundle(), configModeReset)
