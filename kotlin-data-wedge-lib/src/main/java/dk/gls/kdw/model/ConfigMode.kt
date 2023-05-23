package dk.gls.kdw.model

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