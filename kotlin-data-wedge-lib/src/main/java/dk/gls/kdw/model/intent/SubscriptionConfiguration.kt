package dk.gls.kdw.model.intent

import android.os.Bundle

data class SubscriptionConfiguration(
    val enabled: Boolean = true,
    val intentDelivery: IntentDelivery = IntentDelivery.BROADCAST,
)

fun SubscriptionConfiguration.toBundle(): Bundle {
    return Bundle().apply {
        this.putBoolean("intent_output_enabled", enabled)
        this.putString("intent_delivery", intentDelivery.value)
        this.putString("intent_action", "com.zebra.datacapture1.ACTION")
    }
}