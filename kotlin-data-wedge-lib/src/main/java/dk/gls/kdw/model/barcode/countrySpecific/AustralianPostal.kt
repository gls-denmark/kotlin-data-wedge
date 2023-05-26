package dk.gls.kdw.model.barcode.countrySpecific

import android.os.Bundle
import dk.gls.kdw.bundler.Bundler
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AustralianPostal(
    @SerialName("decoder_australian_postal")
    val enabled: Boolean = true,
)

fun AustralianPostal.toBundle() : Bundle = Bundler.bundle(AustralianPostal.serializer(), this)