package dk.gls.kdw.model.barcode

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Chinese20f5(
    @SerialName("decoder_chinese_2of5")
    val enabled: Boolean = true,
)