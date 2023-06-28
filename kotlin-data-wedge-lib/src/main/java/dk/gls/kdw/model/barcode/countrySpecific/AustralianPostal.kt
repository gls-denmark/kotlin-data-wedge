package dk.gls.kdw.model.barcode.countrySpecific

import android.os.Bundle
import dk.gls.kdw.serilization.Bundlizer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AustralianPostal(
    @SerialName("decoder_australian_postal")
    val enabled: Boolean = true
)

fun AustralianPostal.toBundle(): Bundle = Bundlizer.bundle(AustralianPostal.serializer(), this)