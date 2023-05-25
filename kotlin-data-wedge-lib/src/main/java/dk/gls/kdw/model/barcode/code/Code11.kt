package dk.gls.kdw.model.barcode.code

import dk.gls.kdw.model.barcode.model.CheckDigit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Code11(
    @SerialName("decoder_code11")
    val enabled: Boolean = true,
    /**
     * Sets the lower limit when decoding a barcode with a specific length range. For example, to decode a barcode containing between 4 and 12 characters, set this value to 4.
     * Integer from 0–55
     */
    @SerialName("decoder_code11_length1")
    val length1: Int,
    /**
     * 	Sets the upper limit when decoding a barcode with a specific length range. For example, to decode a barcode containing between 4 and 12 characters, set this value to 12.
     * 	Integer from 0–55
     */
    @SerialName("decoder_code11_length2")
    val length2: Int,
    /**
     * Sets the reader to read the barcode twice before accepting data.
     */
    @SerialName("decoder_code11_redundancy")
    val redundancy: Boolean,
    /**
     * Checks the integrity of all Code 11 barcodes to verify that the data complies with the specified check digit algorithm.
     * look at [CheckDigit] for possible values
     */
    @SerialName("decoder_code11_verify_check_digit")
    val verifyCheckDigit: CheckDigit,
    /**
     * Transmits Code 11 data with the check digit.
     */
    @SerialName("decoder_code11_report_check_digit")
    val reportCheckDigit: Boolean
)