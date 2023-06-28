package dk.gls.kdw.model.barcode.countrySpecific

import android.os.Bundle
import dk.gls.kdw.serilization.Bundlizer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Chinese20f5(
    @SerialName("decoder_chinese_2of5")
    val enabled: Boolean = true
)

fun Chinese20f5.toBundle(): Bundle = Bundlizer.bundle(Chinese20f5.serializer(), this)