package dk.gls.kdw.model

import dev.ahmedmourad.bundlizer.Bundlizer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AppConfiguration(
    @SerialName("PACKAGE_NAME")
    val packageName: String = "",
    @SerialName("ACTIVITY_LIST")
    val activityList: ArrayList<String> = arrayListOf("*")
)

fun AppConfiguration.toBundle() = Bundlizer.bundle(AppConfiguration.serializer(), this)