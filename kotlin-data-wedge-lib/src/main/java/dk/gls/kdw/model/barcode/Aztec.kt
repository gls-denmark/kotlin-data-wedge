package dk.gls.kdw.model.barcode

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Aztec(
    @SerialName("decoder_aztec")
    val enabled: Boolean = true,
)