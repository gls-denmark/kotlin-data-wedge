package dk.gls.kdw.model.barcode

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CompositeC(
    @SerialName("decoder_composite_c")
    val enabled: Boolean = true
)