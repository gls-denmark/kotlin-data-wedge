package dk.gls.kdw.model.intent

import android.os.Bundle

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