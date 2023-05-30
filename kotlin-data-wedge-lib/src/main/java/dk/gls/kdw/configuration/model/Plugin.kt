package dk.gls.kdw.configuration.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class Plugin {

    @SerialName("BARCODE")
    BARCODE,

    @SerialName("INTENT")
    INTENT
}