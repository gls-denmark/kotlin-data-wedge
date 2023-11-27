package dk.gls.kdw.implementation

import android.content.Context
import dk.gls.kdw.IKDW
import dk.gls.kdw.configuration.ProfileConfiguration
import dk.gls.kdw.configuration.SetConfigConfiguration
import dk.gls.kdw.configuration.scanner.DataWedgeHardwareScanner
import dk.gls.kdw.configuration.scanner.ParityFlowScannerController
import dk.gls.kdw.configuration.scanner.ScannerController

open class KDWImplementation : IKDW {

    private var dataWedgeHardwareScanner : DataWedgeHardwareScanner? = null

    override fun configure(
        context: Context,
        profileConfiguration: ProfileConfiguration,
        scannerControllerConfiguration: ((dataWedgeHardwareScanner: DataWedgeHardwareScanner) -> ScannerController)?
    ) {
        //Create and broadcast intent configuring scanner
        val dataWedgeIntent = SetConfigConfiguration(
            profileConfiguration = profileConfiguration
        ).toIntent()
        context.sendBroadcast(dataWedgeIntent)

        if(this.dataWedgeHardwareScanner == null) {
            this.dataWedgeHardwareScanner = DataWedgeHardwareScanner(context)
        }


        if(this._scannerController == null) {
            this._scannerController = scannerControllerConfiguration?.invoke(dataWedgeHardwareScanner!!) ?: ParityFlowScannerController(dataWedgeHardwareScanner!!)
        }
    }

    private var _scannerController: ScannerController? = null

    override val scannerController: ScannerController
        @Throws(RuntimeException::class)
        get() = _scannerController
            ?: throw RuntimeException("Accessing KDW.scannerController before calling KDW.configure(...) is not allowed!")
}