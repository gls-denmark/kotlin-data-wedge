package dk.gls.kdw.model.barcode.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class SignatureFormat {
    @SerialName("1")
    JPG,
    @SerialName("2")
    BMP,
    @SerialName("3")
    TIFF
}