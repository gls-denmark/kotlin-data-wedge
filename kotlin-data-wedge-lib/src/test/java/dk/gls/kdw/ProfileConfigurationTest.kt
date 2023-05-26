package dk.gls.kdw

import android.os.Bundle
import dk.gls.kdw.configuration.AppConfiguration
import dk.gls.kdw.configuration.BarcodePluginConfiguration
import dk.gls.kdw.configuration.model.ConfigMode
import dk.gls.kdw.configuration.ProfileConfiguration
import dk.gls.kdw.configuration.toBundle
import dk.gls.kdw.model.barcode.BarcodeConfiguration
import dk.gls.kdw.model.barcode.composite.CompositeAB
import dk.gls.kdw.model.barcode.model.UCCLinkMode
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class ProfileConfigurationTest {

    @Test
    fun profile_configuration_bundle_test() {

        val appConfig = AppConfiguration(
            packageName = "packageName",
            activityList = arrayListOf("1", "2", "3")
        )

        val pluginConfiguration = BarcodePluginConfiguration(
            BarcodeConfiguration(
                compositeAB = CompositeAB(
                    length1 = UCCLinkMode.ALWAYS_LINKED
                )
            )
        )

        val config = ProfileConfiguration(
            enabled = true,
            name = "name",
            configMode = ConfigMode.OVERWRITE,
            pluginConfigurations = arrayListOf(pluginConfiguration),
            appList = arrayListOf(appConfig),

        )

        val bundle: Bundle = config.toBundle()

        Assert.assertTrue(bundle.getBoolean("PROFILE_ENABLED"))
        Assert.assertEquals("name", bundle.getString("PROFILE_NAME"))
        Assert.assertEquals("OVERWRITE", bundle.getString("CONFIG_MODE"))

        val pluginConfigurations = bundle.getParcelableArray("PLUGIN_CONFIG")!! as Array<Bundle>

        val barcodeConfiguration = pluginConfigurations.first()

        Assert.assertEquals("BARCODE", barcodeConfiguration.getString("PLUGIN_NAME"))

        val paramListConfiguration = barcodeConfiguration.getBundle("PARAM_LIST")!!
        Assert.assertTrue(paramListConfiguration.getBoolean("decoder_composite_ab"))
        Assert.assertEquals(1, paramListConfiguration.getInt("decoder_composite_ab_ucc_link_mode"))


        val appArray = bundle.getParcelableArray("APP_LIST")!! as Array<Bundle>

        Assert.assertEquals("packageName", appArray.first().getString("PACKAGE_NAME"))
        Assert.assertEquals(3, appArray.first().getStringArrayList("ACTIVITY_LIST")!!.size)
    }

}