package dk.gls.kdw.model.barcode.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class BitsPerPixel {
    /**
     * 1 BPP - For a black and white image.
     */
    @SerialName("0")
    ONE,

    /**
     * 4 BPP - Assigns 1 of 16 levels of grey to each pixel.
     */
    @SerialName("1")
    FOUR,

    /**
     * 8 BPP - Assigns 1 of 256 levels of grey to each pixel.
     */
    @SerialName("2")
    EIGHT
}