package dk.gls.kdw

import android.content.Context
import dk.gls.kdw.configuration.ProfileConfiguration
import dk.gls.kdw.configuration.scanner.DataWedgeHardwareScanner
import dk.gls.kdw.configuration.scanner.ScannerController
import dk.gls.kdw.model.barcode.ScannerConfiguration

interface IKDW {

    /**
     * Configure the data wedge with the provided [profileConfiguration] and potential custom [ScannerController] implementation
     * @param [context] The context used to send the BroadCastIntent that configures the data wedge
     * @param [profileConfiguration] The desired profile configuration
     * @param [scannerControllerConfiguration] If a custom scanner controller is desired it can be provided. Default is the [ParityFlowScannerController] that minimizes the broadcasted scanner status'es by monitoring current scanner status before broadcasting an intent to the data wedge.
     */
    fun configure(context: Context, profileConfiguration: ProfileConfiguration, scannerControllerConfiguration: ((dataWedgeHardwareScanner: DataWedgeHardwareScanner) -> ScannerController)? = null)

    val scannerController: ScannerController

}