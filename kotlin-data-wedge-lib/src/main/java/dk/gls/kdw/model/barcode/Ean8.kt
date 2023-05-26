package dk.gls.kdw.model.barcode

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Ean8(
    @SerialName("decoder_ean8")
    val enabled: Boolean = true
)