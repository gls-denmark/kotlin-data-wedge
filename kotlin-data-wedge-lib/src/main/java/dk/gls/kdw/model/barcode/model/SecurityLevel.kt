package dk.gls.kdw.model.barcode.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class SecurityLevel {
    /**
     * Security Level 0 - Allows the scanner to operate in its most aggressive state, while providing sufficient security in decoding most "in-spec" barcodes.
     */
    @SerialName("0")
    ZERO,

    /**
     * Security Level 1 - Eliminates most decode failures.
     */
    @SerialName("1")
    ONE,

    /**
     *  Security Level 2 - Select this option if Security level 1 fails to eliminate decode failures.
     */
    @SerialName("2")
    TWO,

    /**
     * Security Level 3 - If Security Level 2 is selected and decode failures still occur, select this security level. Be advised, selecting
     */
    @SerialName("3")
    THREE
}