package dk.gls.kdw.model.barcode.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class CheckDigit {
    @SerialName("0")
    NO,
    @SerialName("1")
    ONE,
    @SerialName("2")
    TWO
}