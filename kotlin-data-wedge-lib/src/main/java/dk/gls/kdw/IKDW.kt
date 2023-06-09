package dk.gls.kdw

import android.content.Context
import dk.gls.kdw.configuration.ProfileConfiguration

interface IKDW {

    fun configure(context: Context, profileConfiguration: ProfileConfiguration)

}