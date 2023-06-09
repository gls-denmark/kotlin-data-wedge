package dk.gls.kdw

import android.content.Context
import android.content.Intent
import dk.gls.kdw.configuration.ProfileConfiguration
import dk.gls.kdw.configuration.toBundle

open class KDWImplementation : IKDW {

    companion object {
        private const val EXTRA_SET_CONFIG = "com.symbol.datawedge.api.SET_CONFIG"
        private const val ACTION_DATA_WEDGE = "com.symbol.datawedge.api.ACTION"
    }

    override fun configure(context: Context, profileConfiguration: ProfileConfiguration) {
        //Create and broadcast intent configuring scanner
        val dataWedgeIntent = Intent()
        dataWedgeIntent.action = ACTION_DATA_WEDGE
        dataWedgeIntent.putExtra(EXTRA_SET_CONFIG, profileConfiguration.toBundle())
        context.sendBroadcast(dataWedgeIntent)
    }

}

object KDW : KDWImplementation()