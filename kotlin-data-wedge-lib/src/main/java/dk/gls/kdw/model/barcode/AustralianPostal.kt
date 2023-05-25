package dk.gls.kdw.model.barcode

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AustralianPostal(
    @SerialName("decoder_australian_postal")
    val enabled: Boolean = true,
)