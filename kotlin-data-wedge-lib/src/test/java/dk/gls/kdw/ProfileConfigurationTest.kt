package dk.gls.kdw

import android.os.Bundle
import dk.gls.kdw.configuration.AppConfiguration
import dk.gls.kdw.configuration.ProfileConfiguration
import dk.gls.kdw.configuration.model.ConfigMode
import dk.gls.kdw.configuration.plugin.BarcodePluginConfiguration
import dk.gls.kdw.configuration.plugin.IntentPluginConfiguration
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
        //region Arrange
        val appConfig = AppConfiguration(
            packageName = "packageName",
            activityList = arrayListOf("1", "2", "3")
        )

        val barcodePluginConfiguration = BarcodePluginConfiguration(
            BarcodeConfiguration(
                compositeAB = CompositeAB(
                    length1 = UCCLinkMode.ALWAYS_LINKED
                )
            )
        )

        val intentPluginConfiguration = IntentPluginConfiguration()

        val bundle = ProfileConfiguration(
            enabled = true,
            name = "name",
            configMode = ConfigMode.OVERWRITE,
            pluginConfigurations = arrayListOf(intentPluginConfiguration, barcodePluginConfiguration),
            appList = arrayListOf(appConfig)
        ).toBundle()

        //endregion

        Assert.assertTrue(bundle.getBoolean("PROFILE_ENABLED"))
        Assert.assertEquals("name", bundle.getString("PROFILE_NAME"))
        Assert.assertEquals("OVERWRITE", bundle.getString("CONFIG_MODE"))

        val pluginConfigurations = bundle.getParcelableArrayList<Bundle>("PLUGIN_CONFIG")!!

        //region Intent plugin configuration
        val intentConfiguration = pluginConfigurations[0]

        Assert.assertEquals("INTENT", intentConfiguration.getString("PLUGIN_NAME"))

        val intentParamListConfiguration = intentConfiguration.getBundle("PARAM_LIST")!!

        Assert.assertTrue(intentParamListConfiguration.getBoolean("intent_output_enabled"))
        Assert.assertEquals("2", intentParamListConfiguration.getString("intent_delivery"))
        Assert.assertEquals("com.zebra.datacapture1.ACTION", intentParamListConfiguration.getString("intent_action"))
        //endregion

        //region Barcode configuration
        val barcodeConfiguration = pluginConfigurations[1]

        Assert.assertEquals("BARCODE", barcodeConfiguration.getString("PLUGIN_NAME"))

        val barcodeParamListConfiguration = barcodeConfiguration.getBundle("PARAM_LIST")!!
        Assert.assertTrue(barcodeParamListConfiguration.getBoolean("decoder_composite_ab"))
        Assert.assertEquals(1, barcodeParamListConfiguration.getInt("decoder_composite_ab_ucc_link_mode"))
        //endregion

        //region App configuration
        val appArray = bundle.getParcelableArray("APP_LIST")!! as Array<Bundle>

        Assert.assertEquals("packageName", appArray.first().getString("PACKAGE_NAME"))
        Assert.assertEquals(3, appArray.first().getStringArray("ACTIVITY_LIST")!!.size)
        //endregion
    }

}