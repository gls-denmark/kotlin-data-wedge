package dk.gls.kdw.model.barcode.code

import android.os.Bundle
import dk.gls.kdw.model.barcode.model.CheckDigit
import dk.gls.kdw.model.barcode.model.SecurityLevel
import dk.gls.kdw.serilization.Bundlizer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Code39(
    @SerialName("decoder_code39")
    val enabled: Boolean = true,
    /**
     * Sets the lower limit when decoding a barcode with a specific length range. For example, to decode a barcode containing between 4 and 12 characters, set this value to 4.
     * Integer from 0–55
     */
    @SerialName("decoder_code39_length1")
    val length1: Int,
    /**
     * Sets the upper limit when decoding a barcode with a specific length range. For example, to decode a barcode containing between 4 and 12 characters, set this value to 12.
     * Integer from 0–55
     */
    @SerialName("decoder_code39_length2")
    val length2: Int,
    /**
     * Sets the reader to read the barcode twice before accepting data.
     */
    @SerialName("decoder_code39_redundancy")
    val redundancy: Boolean,
    /**
     * 	Checks the integrity of all Code 39 barcodes to verify that the data complies with a specified check digit algorithm. The digital scanner decodes only those Code 39 barcodes that include a modulo 43 check digit. Enable this feature only if the Code 39 barcodes contain a modulo 43 check digit.
     */
    @SerialName("decoder_code39_verify_check_digit")
    val verifyCheckDigit: CheckDigit,
    /**
     * Transmits Code 39 data with the check digit.
     */
    @SerialName("decoder_code39_report_check_digit")
    val reportCheckDigit: Boolean,
    /**
     * Enables Code 39 Full ASCII, a variant of Code 39 that pairs characters to encode the full ASCII character set.
     */
    @SerialName("decoder_code39_full_ascii")
    val fillAscii: Boolean,
    /**
     * Converts Code 39 barcode to Code 32, a variant of Code 39 used by the Italian pharmaceutical industry.
     */
    @SerialName("decoder_code39_convert_to_code32")
    val convertToCode32: Boolean = false,
    /**
     * Enables the addition of the prefix character "A" to all Code 32 barcodes.
     */
    @SerialName("decoder_code39_report_code32_prefix")
    val reportCode32Prefix: Boolean = false,
    /**
     * Enables marginless decode
     */
    @SerialName("code39_enable_marginless_decode")
    val enableMarginlessDecode: Boolean,
    /**
     * Specifies the decode security for Code 39 barcodes
     */
    @SerialName("decoder_code39_security_level")
    val securityLevel: SecurityLevel
)

fun Code39.toBundle(): Bundle = Bundlizer.bundle(Code39.serializer(), this)