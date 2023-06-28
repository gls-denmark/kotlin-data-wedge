package dk.gls.kdw.configuration.model

import android.os.Bundle
import dk.gls.kdw.serilization.Bundlizer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Applies to the Profile (from PROFILE_NAME). Can be used in place of CREATE_PROFILE
 */
@Serializable
enum class ConfigMode {
    /**
     * If the Profile does not exist, it creates the Profile and sets the parameters specified in the SET_CONFIG intent. If the profile exists, it updates the parameters specified in the SET_CONFIG intent, while other parameters remain unchanged. RESET_CONFIG flag can be used to reset the plug-in to its default values before applying the new values specified in the intent.
     */
    @SerialName("CREATE_IF_NOT_EXIST")
    CREATE_IF_NOT_EXIST,

    /**
     * If the Profile exists, all options are reset to the default, then the specified settings are applied.
     */
    @SerialName("OVERWRITE")
    OVERWRITE,

    /**
     * Updates only specified settings. The specified Profile must exist in DataWedge. RESET_CONFIG flag can be used to reset the plug-in to its default values before applying the new values specified in the intent.
     */
    @SerialName("UPDATE")
    UPDATE
}

fun ConfigMode.toBundle() : Bundle {
    return Bundlizer.bundle(ConfigMode.serializer(), this)
}