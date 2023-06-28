package dk.gls.kdw.model.barcode.composite

import android.os.Bundle
import dk.gls.kdw.model.barcode.model.UCCLinkMode
import dk.gls.kdw.serilization.Bundlizer
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

fun CompositeAB.toBundle(): Bundle = Bundlizer.bundle(CompositeAB.serializer(), this)