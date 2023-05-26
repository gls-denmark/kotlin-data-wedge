package dk.gls.kdw.model.barcode

import android.os.Bundle
import dk.gls.kdw.bundler.Bundler
import dk.gls.kdw.model.barcode.model.SecurityLevel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class I2of5(
    @SerialName("decoder_i2of5")
    val enabled: Boolean = true,

    /**
     * Sets the lower limit when decoding a barcode with a specific length range. For example, to decode a barcode containing between 4 and 12 characters, set this value to 4.
     * Integer from 0–55
     */
    @SerialName("decoder_i2of5_length1")
    val length1: Int,

    /**
     * Sets the upper limit when decoding a barcode with a specific length range. For example, to decode a barcode containing between 4 and 12 characters, set this value to 12.
     * Integer from 0–55
     */
    @SerialName("decoder_i2of5_length2")
    val length2: Int,

    /**
     * Sets the reader to read the barcode twice before accepting data.
     */
    @SerialName("decoder_i2of5_redundancy")
    val redundancy: Boolean,

    /**
     * 	Transmits Interleaved 2 of 5 data with the check digit when enabled.
     */
    @SerialName("decoder_code11_report_check_digit")
    val reportCheckDigit: Boolean,

    /**
     * Converts 14-character Interleaved 2 of 5 barcodes to EAN-13 and transmits as EAN-13. The Interleaved 2 of 5 barcode must be enabled and must have a leading zero and a valid EAN-13 check digit.
     */
    @SerialName("decoder_itf14_convert_to_ean13")
    val convertToEan13: Boolean,

    /**
     * 	Enable marginless decode
     */
    @SerialName("i20f5_enable_marginless_decode")
    val marginlessDecode: Boolean,

    /**
     * When enabled, inserts special check characters into the transmitted data stream of Interleaved 2 of 5 barcodes that are of length 14 characters and meet specific Febraban criteria.
     */
    @SerialName("decoder_i2of5_enable_febraban")
    val enableFebraban: Boolean,

    /**
     * 	Checks the integrity of all I2of5 barcodes to verify that the data complies with either the specified Uniform Symbology Specification (USS) or the Optical Product Code Council (OPCC) check digit algorithm.
     */
    @SerialName("decoder_i2of5_check_digit")
    val checkDigit: Boolean,

    /**
     * The scanner offers four levels of decode security for I2of5 barcodes. As the quality of barcodes decreases, implementing an increased level of security compensates and helps improve decoding success. There is an inverse relationship between scanner aggressiveness and security. Zebra recommends to carefully choose the level of security necessary for any given application.
     */
    @SerialName("decoder_i2of5_security_level")
    val securityLevel: SecurityLevel
) : Bundleable {
    override fun toBundle(): Bundle {
        return Bundler.bundle(serializer(), this)
    }
}