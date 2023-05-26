package dk.gls.kdw.model.barcode.countrySpecific

import android.os.Bundle
import dk.gls.kdw.bundler.Bundler
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CanadianPostal(
    @SerialName("decoder_canadian_postal")
    val enabled: Boolean = true,
)

fun CanadianPostal.toBundle() : Bundle = Bundler.bundle(CanadianPostal.serializer(), this)