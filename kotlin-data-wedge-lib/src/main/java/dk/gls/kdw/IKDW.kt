package dk.gls.kdw

import android.content.Context
import dk.gls.kdw.configuration.ProfileConfiguration
import dk.gls.kdw.configuration.scanner.ScannerController
import dk.gls.kdw.model.barcode.ScannerConfiguration

interface IKDW {

    fun configure(context: Context, profileConfiguration: ProfileConfiguration)

    val scannerController: ScannerController

}