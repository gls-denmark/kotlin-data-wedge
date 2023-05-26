package dk.gls.kdw

import dk.gls.kdw.model.barcode.BarcodeConfiguration
import dk.gls.kdw.model.barcode.matrix.DataMatrix
import dk.gls.kdw.model.barcode.I2of5
import dk.gls.kdw.model.barcode.matrix.MatrixI2of5
import dk.gls.kdw.model.barcode.ScannerConfiguration
import dk.gls.kdw.model.barcode.model.SecurityLevel
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class BarcodeConfigurationTest {

    @Test
    fun barcode_configuration_test() {

        val dataMatrix = DataMatrix()
        val i2of5 = I2of5(
            length1 = 12,
            length2 = 12,
            redundancy = true,
            reportCheckDigit = false,
            convertToEan13 = false,
            marginlessDecode = false,
            enableFebraban = false,
            checkDigit = false,
            securityLevel = SecurityLevel.ONE
        )

        val matrixI2Of5 = MatrixI2of5(
            length1 = 12,
            length2 = 12,
            redundancy = true,
            reportCheckDigit = false,
            verifyCheckDigit = false
        )


        val barcodeConfiguration = BarcodeConfiguration(
            scannerConfiguration = ScannerConfiguration(),
            dataMatrix = dataMatrix,
            i2of5 = i2of5,
            matrixI2of5 = matrixI2Of5
        ).toBundle()

        val barcodePluginConfig = barcodeConfiguration.getBundle("PARAM_LIST")!!
        Assert.assertTrue(barcodePluginConfig.getBoolean("decoder_datamatrix"))

        Assert.assertEquals(barcodePluginConfig.getInt("decoder_i2of5_length1"), 12)
        Assert.assertEquals(barcodePluginConfig.getInt("decoder_i2of5_length2"), 12)
        Assert.assertEquals(barcodePluginConfig.getBoolean("decoder_i2of5_redundancy"), true)
        
        Assert.assertTrue(barcodePluginConfig.getBoolean("decoder_matrix_2of5"))
        Assert.assertEquals(barcodePluginConfig.getInt("decoder_matrix_2of5_length1"), 12)
        Assert.assertEquals(barcodePluginConfig.getInt("decoder_matrix_2of5_length2"), 12)
        Assert.assertTrue(barcodePluginConfig.getBoolean("decoder_matrix_2of5_redundancy"))
    }

}