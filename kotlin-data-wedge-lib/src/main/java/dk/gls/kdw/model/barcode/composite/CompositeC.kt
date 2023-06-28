package dk.gls.kdw.model.barcode.composite

import android.os.Bundle
import dk.gls.kdw.serilization.Bundlizer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CompositeC(
    @SerialName("decoder_composite_c")
    val enabled: Boolean = true
)

fun CompositeC.toBundle(): Bundle = Bundlizer.bundle(CompositeC.serializer(), this)