package dk.gls.kdw.model.barcode.ean

import android.os.Bundle
import dk.gls.kdw.bundler.Bundler
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Ean13(
    @SerialName("decoder_ean13")
    val enabled: Boolean = true
)

fun Ean13.toBundle(): Bundle = Bundler.bundle(Ean13.serializer(), this)