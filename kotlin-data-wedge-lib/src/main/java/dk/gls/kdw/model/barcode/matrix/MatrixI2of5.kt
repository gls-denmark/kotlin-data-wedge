package dk.gls.kdw.model.barcode.matrix

import android.os.Bundle
import dk.gls.kdw.bundler.Bundler
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MatrixI2of5(
    @SerialName("decoder_matrix_2of5")
    val enabled: Boolean = true,
    /**
     * Sets the lower limit when decoding a barcode with a specific length range. For example, to decode a barcode containing between 4 and 12 characters, set this value to 4.
     * Integer from 0–55
     */
    @SerialName("decoder_matrix_2of5_length1")
    val length1: Int,
    /**
     * 	Sets the upper limit when decoding a barcode with a specific length range. For example, to decode a barcode containing between 4 and 12 characters, set this value to 12.
     * 	Integer from 0–55
     */
    @SerialName("decoder_matrix_2of5_length2")
    val length2: Int,
    /**
     * Sets the reader to read the barcode twice before accepting data.
     */
    @SerialName("decoder_matrix_2of5_redundancy")
    val redundancy: Boolean,
    /**
     * When enabled, transmits Matrix 2 of 5 data with the check digit.
     */
    @SerialName("decoder_matrix_2of5_report_check_digit")
    val reportCheckDigit: Boolean,
    /**
     * Checks the integrity of all Matrix 2 of 5 barcodes to verify that the data complies with a specified check digit algorithm.
     */
    @SerialName("decoder_matrix_2of5_verify_check_digit")
    val verifyCheckDigit: Boolean,
)

fun MatrixI2of5.toBundle(): Bundle = Bundler.bundle(MatrixI2of5.serializer(), this)