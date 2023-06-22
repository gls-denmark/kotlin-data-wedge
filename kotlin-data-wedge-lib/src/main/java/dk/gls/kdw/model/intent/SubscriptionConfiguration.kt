package dk.gls.kdw.model.intent

import android.os.Bundle

/**
 * The subscription configuration defines how data is delivered to the subscribing app
 * [enabled] Whether the configuration is enabled
 * [intentDelivery] Defines how the data should be delivered to the app. Default [IntentDelivery.BROADCAST]. BE AWARE If this is changed, the [ScannerController] on the KDW instance does not work as intended.
 */
data class SubscriptionConfiguration(
    val enabled: Boolean = true,
    val intentDelivery: IntentDelivery = IntentDelivery.BROADCAST,
)

fun SubscriptionConfiguration.toBundle(): Bundle {
    return Bundle().apply {
        this.putString("intent_output_enabled", enabled.toString())
        this.putInt("intent_delivery", intentDelivery.value)
        this.putString("intent_action", "com.zebra.datacapture1.ACTION")
    }
}