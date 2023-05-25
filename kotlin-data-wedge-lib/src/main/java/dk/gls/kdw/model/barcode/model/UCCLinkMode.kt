package dk.gls.kdw.model.barcode.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class UCCLinkMode {
    /**
     * UPC Never Linked - Transmit UPC barcodes regardless of whether a 2D barcode is detected.
     */
    @SerialName("0")
    LINK_FLAG_IGNORED,

    /**
     * UPC Always Linked - Transmit UPC barcodes and the 2D portion. If 2D is not present, do not transmit the barcode.
     */
    @SerialName("1")
    ALWAYS_LINKED,

    /**
     * Autodiscriminate UPC Composites - The scanner determines if there is a 2D portion, then transmits the UPC, as well as the 2D portion if present.
     */
    @SerialName("2")
    AUTO_DISCRIMINATE,
}