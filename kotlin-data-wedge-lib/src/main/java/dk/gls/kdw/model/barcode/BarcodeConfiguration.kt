package dk.gls.kdw.model.barcode

import android.os.Bundle
import dk.gls.kdw.model.barcode.code.Code11
import dk.gls.kdw.model.barcode.code.Code128
import dk.gls.kdw.model.barcode.code.Code32
import dk.gls.kdw.model.barcode.code.Code39
import dk.gls.kdw.model.barcode.code.Code93
import dk.gls.kdw.model.barcode.code.toBundle
import dk.gls.kdw.model.barcode.composite.CompositeAB
import dk.gls.kdw.model.barcode.composite.CompositeC
import dk.gls.kdw.model.barcode.composite.toBundle
import dk.gls.kdw.model.barcode.countrySpecific.AustralianPostal
import dk.gls.kdw.model.barcode.countrySpecific.CanadianPostal
import dk.gls.kdw.model.barcode.countrySpecific.Chinese20f5
import dk.gls.kdw.model.barcode.countrySpecific.toBundle
import dk.gls.kdw.model.barcode.ean.Ean13
import dk.gls.kdw.model.barcode.ean.Ean8
import dk.gls.kdw.model.barcode.ean.toBundle
import dk.gls.kdw.model.barcode.matrix.DataMatrix
import dk.gls.kdw.model.barcode.matrix.MatrixI2of5
import dk.gls.kdw.model.barcode.matrix.toBundle

class BarcodeConfiguration(
    val scannerConfiguration: ScannerConfiguration,
    val australianPostal: AustralianPostal? = null,
    val aztec: Aztec? = null,
    val canadianPostal: CanadianPostal? = null,
    val chinese20f5: Chinese20f5? = null,
    val codabar: Codabar? = null,
    val code11: Code11? = null,
    val code32: Code32? = null,
    val code39: Code39? = null,
    val code93: Code93? = null,
    val code128: Code128? = null,
    val compositeAB: CompositeAB? = null,
    val compositeC: CompositeC? = null,
    val dataMatrix: DataMatrix? = null,
    val decoderSignature: DecoderSignature? = null,
    val ean8: Ean8? = null,
    val ean13: Ean13? = null,
    val i2of5: I2of5? = null,
    val matrixI2of5: MatrixI2of5? = null,
) {

    fun toBundle(): Bundle {
        val barcodeConfig = Bundle()
        barcodeConfig.putString("PLUGIN_NAME", "BARCODE")
        barcodeConfig.putString("RESET_CONFIG", "true")

        val barcodeConfigPropertyBundle = Bundle()

        australianPostal?.let {
            barcodeConfigPropertyBundle.putAll(it.toBundle())
        }
        aztec?.let {
            barcodeConfigPropertyBundle.putAll(it.toBundle())
        }
        canadianPostal?.let {
            barcodeConfigPropertyBundle.putAll(it.toBundle())
        }
        chinese20f5?.let {
            barcodeConfigPropertyBundle.putAll(it.toBundle())
        }
        codabar?.let {
            barcodeConfigPropertyBundle.putAll(it.toBundle())
        }
        code11?.let {
            barcodeConfigPropertyBundle.putAll(it.toBundle())
        }
        code32?.let {
            barcodeConfigPropertyBundle.putAll(it.toBundle())
        }
        code39?.let {
            barcodeConfigPropertyBundle.putAll(it.toBundle())
        }
        code93?.let {
            barcodeConfigPropertyBundle.putAll(it.toBundle())
        }
        code128?.let {
            barcodeConfigPropertyBundle.putAll(it.toBundle())
        }
        compositeAB?.let {
            barcodeConfigPropertyBundle.putAll(it.toBundle())
        }
        compositeC?.let {
            barcodeConfigPropertyBundle.putAll(it.toBundle())
        }
        dataMatrix?.let {
            barcodeConfigPropertyBundle.putAll(it.toBundle())
        }
        decoderSignature?.let {
            barcodeConfigPropertyBundle.putAll(it.toBundle())
        }
        ean8?.let {
            barcodeConfigPropertyBundle.putAll(it.toBundle())
        }
        ean13?.let {
            barcodeConfigPropertyBundle.putAll(it.toBundle())
        }
        i2of5?.let {
            barcodeConfigPropertyBundle.putAll(it.toBundle())
        }
        matrixI2of5?.let {
            barcodeConfigPropertyBundle.putAll(it.toBundle())
        }

        barcodeConfig.putBundle("PARAM_LIST", barcodeConfigPropertyBundle)

        return barcodeConfig
    }

}