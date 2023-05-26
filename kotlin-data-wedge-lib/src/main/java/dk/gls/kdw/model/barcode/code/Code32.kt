package dk.gls.kdw.model.barcode.code

import android.os.Bundle
import dk.gls.kdw.bundler.Bundler
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Code32(
    @SerialName("decoder_code32")
    val enabled: Boolean = true
)

fun Code32.toBundle(): Bundle = Bundler.bundle(Code32.serializer(), this)