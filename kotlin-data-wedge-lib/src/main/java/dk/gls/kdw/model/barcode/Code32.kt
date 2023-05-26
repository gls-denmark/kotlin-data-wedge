package dk.gls.kdw.model.barcode

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Code32(
    @SerialName("decoder_code32")
    val enabled: Boolean = true
)