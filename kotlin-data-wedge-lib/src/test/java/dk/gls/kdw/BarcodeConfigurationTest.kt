package dk.gls.kdw

import dk.gls.kdw.model.barcode.BarcodeConfiguration
import dk.gls.kdw.model.barcode.DecoderSignature
import dk.gls.kdw.model.barcode.I2of5
import dk.gls.kdw.model.barcode.ScannerConfiguration
import dk.gls.kdw.model.barcode.code.Code11
import dk.gls.kdw.model.barcode.matrix.DataMatrix
import dk.gls.kdw.model.barcode.matrix.MatrixI2of5
import dk.gls.kdw.model.barcode.model.BitsPerPixel
import dk.gls.kdw.model.barcode.model.CheckDigit
import dk.gls.kdw.model.barcode.model.SecurityLevel
import dk.gls.kdw.model.barcode.model.SignatureFormat
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class BarcodeConfigurationTest {

    @Test
    fun barcode_configuration_bundle_created_correct() {

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
            securityLevel = SecurityLevel.THREE
        )

        val matrixI2Of5 = MatrixI2of5(
            length1 = 12,
            length2 = 12,
            redundancy = true,
            reportCheckDigit = false,
            verifyCheckDigit = false
        )

        val code11 = Code11(
            length1 = 10,
            length2 = 10,
            redundancy = false,
            verifyCheckDigit = CheckDigit.TWO,
            reportCheckDigit = false
        )

        val decoderSignature = DecoderSignature(
            format = SignatureFormat.BMP,
            bitsPerPixel = BitsPerPixel.EIGHT
        )

        val barcodeConfigBundle = BarcodeConfiguration(
            scannerConfiguration = ScannerConfiguration(),
            dataMatrix = dataMatrix,
            i2of5 = i2of5,
            matrixI2of5 = matrixI2Of5,
            code11 = code11,
            decoderSignature = decoderSignature
        ).toBundle()

        Assert.assertEquals("AUTO", barcodeConfigBundle.getString("scanner_selection"))
        Assert.assertEquals(true, barcodeConfigBundle.getBoolean("scanner_input_enabled"))

        Assert.assertTrue(barcodeConfigBundle.getBoolean("decoder_datamatrix"))

        /** i2of5 **/
        Assert.assertEquals(12, barcodeConfigBundle.getInt("decoder_i2of5_length1"))
        Assert.assertEquals(12, barcodeConfigBundle.getInt("decoder_i2of5_length2"))
        Assert.assertEquals(true, barcodeConfigBundle.getBoolean("decoder_i2of5_redundancy"))
        Assert.assertEquals(3, barcodeConfigBundle.getInt("decoder_i2of5_security_level"))

        /** 2of5 **/
        Assert.assertTrue(barcodeConfigBundle.getBoolean("decoder_matrix_2of5"))
        Assert.assertEquals(12, barcodeConfigBundle.getInt("decoder_matrix_2of5_length1"))
        Assert.assertEquals(12, barcodeConfigBundle.getInt("decoder_matrix_2of5_length2"))
        Assert.assertTrue(barcodeConfigBundle.getBoolean("decoder_matrix_2of5_redundancy"))

        /** code 11 **/
        Assert.assertTrue(barcodeConfigBundle.getBoolean("decoder_code11"))
        Assert.assertEquals(10, barcodeConfigBundle.getInt("decoder_code11_length1"))
        Assert.assertEquals(10, barcodeConfigBundle.getInt("decoder_code11_length2"))
        Assert.assertFalse(barcodeConfigBundle.getBoolean("decoder_code11_redundancy"))
        Assert.assertEquals(2, barcodeConfigBundle.getInt("decoder_code11_verify_check_digit"))
        Assert.assertFalse(barcodeConfigBundle.getBoolean("decoder_code11_report_check_digit"))

        /** DecoderSignature **/
        Assert.assertTrue(barcodeConfigBundle.getBoolean("decoder_signature"))
        Assert.assertEquals(2, barcodeConfigBundle.getInt("decoder_signature_format"))
        Assert.assertEquals(400, barcodeConfigBundle.getInt("decoder_signature_width"))
        Assert.assertEquals(400, barcodeConfigBundle.getInt("decoder_signature_height"))
        Assert.assertEquals(65, barcodeConfigBundle.getInt("decoder_signature_jpegquality"))
        Assert.assertEquals(2, barcodeConfigBundle.getInt("decoder_signature_bpp"))
    }

}