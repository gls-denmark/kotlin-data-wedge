package dk.gls.kdw.model.barcode.countrySpecific

import android.os.Bundle
import dev.ahmedmourad.bundlizer.Bundlizer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CanadianPostal(
    @SerialName("decoder_canadian_postal")
    val enabled: Boolean = true
)

fun CanadianPostal.toBundle(): Bundle = Bundlizer.bundle(CanadianPostal.serializer(), this)