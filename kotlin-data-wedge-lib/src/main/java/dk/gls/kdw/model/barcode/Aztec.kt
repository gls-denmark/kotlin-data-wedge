package dk.gls.kdw.model.barcode

import android.os.Bundle
import dk.gls.kdw.serilization.Bundlizer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Aztec(
    @SerialName("decoder_aztec")
    val enabled: Boolean = true
)

fun Aztec.toBundle(): Bundle = Bundlizer.bundle(Aztec.serializer(), this)