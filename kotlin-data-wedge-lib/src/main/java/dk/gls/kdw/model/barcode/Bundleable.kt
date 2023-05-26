package dk.gls.kdw.model.barcode

import android.os.Bundle
import kotlinx.serialization.Serializable

@Serializable
sealed interface Bundleable {
    fun toBundle(): Bundle
}

fun <T : Bundle> T.addToBundle(childBundle: Bundleable) {
    val bundle = childBundle.toBundle()
    this.putAll(bundle)
}