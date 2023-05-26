package dk.gls.kdw.model.barcode.ean

import android.os.Bundle
import dk.gls.kdw.bundler.Bundler
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Ean8(
    @SerialName("decoder_ean8")
    val enabled: Boolean = true
)

fun Ean8.toBundle(): Bundle = Bundler.bundle(Ean8.serializer(), this)