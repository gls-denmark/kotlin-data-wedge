package dk.gls.kdw.model

import dk.gls.kdw.bundelizer.Bundler
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AppConfiguration(
    @SerialName("PACKAGE_NAME")
    val packageName: String = "",
    @SerialName("ACTIVITY_LIST")
    val activityList: ArrayList<String> = arrayListOf("*")
)

fun AppConfiguration.toBundle() = Bundler.bundle(AppConfiguration.serializer(), this)