package dk.gls.kdw

import android.os.Bundle
import dk.gls.kdw.bundler.unbundle
import dk.gls.kdw.model.AppConfiguration
import dk.gls.kdw.model.ConfigMode
import dk.gls.kdw.model.ProfileConfiguration
import dk.gls.kdw.model.toBundle
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class ProfileConfigurationTest {

    @Test
    fun profile_configuration_bundle_test() {

        val appConfig = AppConfiguration(
            "packageName",
            arrayListOf("1", "2", "3")
        )

        val config = ProfileConfiguration(
            name = "name",
            enabled = true,
            configMode = ConfigMode.OVERWRITE,
            appList = arrayListOf(appConfig)
        )

        val bundle: Bundle = config.toBundle()

        val unBundled : ProfileConfiguration = bundle.unbundle(ProfileConfiguration.serializer())

        Assert.assertEquals("packageName", unBundled.appList.first().packageName)
        Assert.assertEquals(3, unBundled.appList.first().activityList.size)
    }

}