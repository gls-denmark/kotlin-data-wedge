package dk.gls.kdw

import android.content.Context
import dk.gls.kdw.configuration.ProfileConfiguration
import dk.gls.kdw.configuration.SetConfigConfiguration
import dk.gls.kdw.configuration.scanner.DataWedgeHardwareScanner
import dk.gls.kdw.configuration.scanner.ParityFlowScannerController
import dk.gls.kdw.configuration.scanner.ScannerController

open class KDWImplementation : IKDW {

    override fun configure(
        context: Context,
        profileConfiguration: ProfileConfiguration,
        scannerController: ScannerController?
    ) {
        //Create and broadcast intent configuring scanner
        val dataWedgeIntent = SetConfigConfiguration(
            profileConfiguration = profileConfiguration
        ).toIntent()
        context.sendBroadcast(dataWedgeIntent)

        this._scannerController = scannerController ?: ParityFlowScannerController()

        this._scannerController.apply {
            this!!.dataWedgeHardwareScanner = DataWedgeHardwareScanner(context)
        }
    }

    private var _scannerController: ScannerController? = null

    override val scannerController: ScannerController
        @Throws(RuntimeException::class)
        get() = _scannerController
            ?: throw RuntimeException("Accessing KDW.scannerController before calling KDW.configure(...) is not allowed!")
}

object KDW : KDWImplementation()