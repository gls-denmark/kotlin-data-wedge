package dk.gls.kdw.configuration

import android.os.Bundle
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class AppConfiguration(
    val packageName: String = "",
    val activityList: ArrayList<String> = arrayListOf("*")
)

fun AppConfiguration.toBundle(): Bundle {
    return Bundle().apply {
        this@apply.putString("PACKAGE_NAME", packageName)
        this@apply.putStringArrayList("ACTIVITY_LIST", activityList)
    }
}