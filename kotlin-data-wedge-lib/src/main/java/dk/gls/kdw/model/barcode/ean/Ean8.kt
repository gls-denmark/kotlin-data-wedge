package dk.gls.kdw.model.barcode.ean

import android.os.Bundle
import dev.ahmedmourad.bundlizer.Bundlizer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Ean8(
    @SerialName("decoder_ean8")
    val enabled: Boolean = true
)

fun Ean8.toBundle(): Bundle = Bundlizer.bundle(Ean8.serializer(), this)