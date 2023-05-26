package dk.gls.kdw.model.barcode.composite

import android.os.Bundle
import dk.gls.kdw.bundler.Bundler
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CompositeC(
    @SerialName("decoder_composite_c")
    val enabled: Boolean = true
)

fun CompositeC.toBundle(): Bundle = Bundler.bundle(CompositeC.serializer(), this)