package dk.gls.kdw.model.barcode

import dk.gls.kdw.model.barcode.model.UCCLinkMode
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CompositeAB(
    @SerialName("decoder_composite_ab")
    val enabled: Boolean = true,
    /**
     * Select an option for linking UPC barcodes with a 2D barcode during transmission as if they were one barcode
     */
    @SerialName("decoder_composite_ab_ucc_link_mode")
    val length1: UCCLinkMode,
)