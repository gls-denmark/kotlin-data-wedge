package dk.gls.kdw.model.barcode

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DataMatrix(
    @SerialName("decoder_datamatrix")
    val enabled: Boolean = true
)