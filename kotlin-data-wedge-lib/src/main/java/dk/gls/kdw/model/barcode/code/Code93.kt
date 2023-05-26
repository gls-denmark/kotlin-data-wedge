package dk.gls.kdw.model.barcode.code

import android.os.Bundle
import dev.ahmedmourad.bundlizer.Bundlizer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Code93(
    @SerialName("decoder_code93")
    val enabled: Boolean = true,
    /**
     * Sets the lower limit when decoding a barcode with a specific length range. For example, to decode a barcode containing between 4 and 12 characters, set this value to 4.
     * Integer from 0–55
     */
    @SerialName("decoder_code93_length1")
    val length1: Int,
    /**
     * 	Sets the upper limit when decoding a barcode with a specific length range. For example, to decode a barcode containing between 4 and 12 characters, set this value to 12.
     * Integer from 0–55
     */
    @SerialName("decoder_code93_length2")
    val length2: Int,
    /**
     * Sets the reader to read the barcode twice before accepting data.
     */
    @SerialName("decoder_code93_redundancy")
    val redundancy: Boolean,
)

fun Code93.toBundle(): Bundle = Bundlizer.bundle(Code93.serializer(), this)