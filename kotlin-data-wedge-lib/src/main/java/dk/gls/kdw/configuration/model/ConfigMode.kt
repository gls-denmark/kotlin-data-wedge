package dk.gls.kdw.configuration.model

import android.os.Bundle
import dev.ahmedmourad.bundlizer.Bundlizer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class ConfigMode {
    @SerialName("CREATE_IF_NOT_EXIST")
    CREATE_IF_NOT_EXIST,
    @SerialName("OVERWRITE")
    OVERWRITE,
    @SerialName("UPDATE")
    UPDATE
}

fun ConfigMode.toBundle() : Bundle {
    return Bundlizer.bundle(ConfigMode.serializer(), this)
}