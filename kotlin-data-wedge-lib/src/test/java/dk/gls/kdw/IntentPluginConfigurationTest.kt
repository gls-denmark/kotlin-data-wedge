package dk.gls.kdw

import dk.gls.kdw.configuration.plugin.IntentPluginConfiguration
import dk.gls.kdw.configuration.plugin.toBundle
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class IntentPluginConfigurationTest {

    @Test
    fun intent_configuration_test() {

        val intentPluginConfiguration = IntentPluginConfiguration()
            .toBundle()

        Assert.assertEquals("INTENT", intentPluginConfiguration.getString("PLUGIN_NAME"))

        val paramListConfiguration = intentPluginConfiguration.getBundle("PARAM_LIST")!!

        Assert.assertEquals(paramListConfiguration.getString("intent_output_enabled"), "true")
        Assert.assertEquals(2, paramListConfiguration.getInt("intent_delivery"))
        Assert.assertEquals("com.zebra.datacapture1.ACTION", paramListConfiguration.getString("intent_action"))
    }

}