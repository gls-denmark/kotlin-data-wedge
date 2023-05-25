package dk.gls.kdw.model.barcode.ean

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Ean13(
    @SerialName("decoder_ean13")
    val enabled: Boolean = true
)