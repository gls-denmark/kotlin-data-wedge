package dk.gls.kdw.configuration

import android.os.Bundle
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * The app configuration configures which packageName the given configuration should apply too
 * @param [packageName] The package name of the app the configuration should be enabled for
 * @param [activityList] The activities in the app the configuration should be enabled in. Default = listOf(*), which enables it in the entire application.
 */
data class AppConfiguration(
    val packageName: String,
    val activityList: List<String> = listOf("*")
)

fun AppConfiguration.toBundle(): Bundle {
    return Bundle().apply {
        this@apply.putString("PACKAGE_NAME", packageName)
        this@apply.putStringArray("ACTIVITY_LIST", activityList.toTypedArray())
    }
}