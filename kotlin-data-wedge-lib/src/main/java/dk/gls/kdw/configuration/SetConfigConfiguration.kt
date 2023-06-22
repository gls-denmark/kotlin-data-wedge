package dk.gls.kdw.configuration

import android.content.Intent

/**
 * The Set Config configuration is converted to an intent before being broadcasted to the data wedge
 * @see <a href="https://techdocs.zebra.com/datawedge/latest/guide/api/setconfig/">Official documentation </a>
 **/
data class SetConfigConfiguration(
    val profileConfiguration: ProfileConfiguration
) {
    companion object {
        private const val EXTRA_SET_CONFIG = "com.symbol.datawedge.api.SET_CONFIG"
        private const val ACTION_DATA_WEDGE = "com.symbol.datawedge.api.ACTION"
    }

    fun toIntent() : Intent {
        val dataWedgeIntent = Intent()
        dataWedgeIntent.action = ACTION_DATA_WEDGE
        dataWedgeIntent.putExtra(EXTRA_SET_CONFIG, profileConfiguration.toBundle())

        return dataWedgeIntent
    }
}