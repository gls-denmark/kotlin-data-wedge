package dk.gls.kdw.model.barcode

import android.os.Bundle
import dk.gls.kdw.bundler.Bundler
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Aztec(
    @SerialName("decoder_aztec")
    val enabled: Boolean = true,
)

fun Aztec.toBundle() : Bundle = Bundler.bundle(Aztec.serializer(), this)