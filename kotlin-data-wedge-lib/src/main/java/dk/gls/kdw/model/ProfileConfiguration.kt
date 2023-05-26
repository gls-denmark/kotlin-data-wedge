package dk.gls.kdw.model

import android.os.Bundle
import dk.gls.kdw.bundler.Bundler
import dk.gls.kdw.model.barcode.BarcodeConfiguration
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 *
 *
 * @see <a href="https://techdocs.zebra.com/datawedge/latest/guide/api/setconfig/">Official documentation </a>
 **/
@Serializable
class ProfileConfiguration(
    @SerialName("PROFILE_NAME")
    val name: String,
    @SerialName("PROFILE_ENABLED")
    val enabled: Boolean = true,
    @SerialName("CONFIG_MODE")
    val configMode: ConfigMode,
    @SerialName("APP_LIST")
    val appList: ArrayList<AppConfiguration>
) {

}

fun ProfileConfiguration.toBundle() : Bundle = Bundler.bundle(ProfileConfiguration.serializer(), this)