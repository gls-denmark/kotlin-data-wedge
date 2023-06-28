package dk.gls.kdw.model.barcode

import android.os.Bundle
import dk.gls.kdw.serilization.Bundlizer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Codabar is also known as NW-7.
 */
@Serializable
data class Codabar(
    @SerialName("decoder_codabar")
    val enabled: Boolean = true,
    /**
     * Sets the lower limit when decoding a barcode with a specific length range. For example, to decode a barcode containing between 8 and 24 characters, set this value to 8.
     * Integer from 0–55
     */
    @SerialName("decoder_codabar_length1")
    val length1: Int,
    /**
     * 	Sets the upper limit when decoding a barcode with a specific length range. For example, to decode a barcode containing between 8 and 24 characters, set this value to 24.
     * 	Integer from 0–55
     */
    @SerialName("decoder_codabar_length2")
    val length2: Int,
    /**
     * Sets the reader to read the barcode twice before accepting data.
     */
    @SerialName("decoder_codabar_redundancy")
    val redundancy: Boolean,
    /**
     * 	Strips the start and stop characters and inserts a space after the first, fifth, and tenth characters of a 14-character Codabar barcode. Enable this feature if the host system requires this data format.
     */
    @SerialName("decoder_codabar_clsi_editing")
    val clsiEditing: Boolean,
    /**
     * 	Strips the start and stop characters from a decoded Codabar barcode. Enable this feature if the host system requires this data format.
     */
    @SerialName("decoder_codabar_notis_editing")
    val notisEditing: Boolean
)

fun Codabar.toBundle(): Bundle = Bundlizer.bundle(Codabar.serializer(), this)