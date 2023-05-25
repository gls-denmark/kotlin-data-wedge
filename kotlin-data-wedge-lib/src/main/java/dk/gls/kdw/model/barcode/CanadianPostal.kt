package dk.gls.kdw.model.barcode

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CanadianPostal(
    @SerialName("decoder_canadian_postal")
    val enabled: Boolean = true,
)