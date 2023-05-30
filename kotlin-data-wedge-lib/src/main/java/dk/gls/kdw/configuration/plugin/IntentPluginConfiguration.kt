package dk.gls.kdw.configuration.plugin

import dk.gls.kdw.configuration.model.Plugin
import dk.gls.kdw.model.intent.SubscriptionConfiguration
import dk.gls.kdw.model.intent.toBundle

data class IntentPluginConfiguration(
    val subscriptionConfiguration: SubscriptionConfiguration = SubscriptionConfiguration(),
    override val configModeReset: Boolean = true
) : PluginConfiguration(Plugin.INTENT, subscriptionConfiguration.toBundle(), configModeReset)
