package dk.gls.kdw.configuration

import android.os.Bundle
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

//@Serializable
data class AppConfiguration(
    //@SerialName("PACKAGE_NAME")
    val packageName: String = "",
    //@SerialName("ACTIVITY_LIST")
    val activityList: ArrayList<String> = arrayListOf("*")
)

fun AppConfiguration.toBundle(): Bundle {
    return Bundle().apply {
        this@apply.putString("PACKAGE_NAME", packageName)
        this@apply.putStringArrayList("ACTIVITY_LIST", activityList)
    }
}