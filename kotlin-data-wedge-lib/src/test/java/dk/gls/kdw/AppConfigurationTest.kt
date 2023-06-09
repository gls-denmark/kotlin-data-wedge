package dk.gls.kdw

import android.os.Bundle
import dk.gls.kdw.configuration.AppConfiguration
import dk.gls.kdw.configuration.toBundle
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class AppConfigurationTest {

    @Test
    fun app_configuration_bundle_test() {

        val config = AppConfiguration(
            "packageName",
            arrayListOf("1", "2", "3")
        )

        val bundle: Bundle = config.toBundle()

        Assert.assertEquals("packageName", bundle.getString("PACKAGE_NAME"))
        Assert.assertEquals(3, bundle.getStringArray("ACTIVITY_LIST")!!.size)
    }

}