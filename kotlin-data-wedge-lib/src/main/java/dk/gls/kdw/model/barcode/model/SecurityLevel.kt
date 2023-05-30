package dk.gls.kdw.model.barcode.model

enum class SecurityLevel {
    /**
     * Security Level 0 - Allows the scanner to operate in its most aggressive state, while providing sufficient security in decoding most "in-spec" barcodes.
     */
    ZERO,

    /**
     * Security Level 1 - Eliminates most decode failures.
     */
    ONE,

    /**
     *  Security Level 2 - Select this option if Security level 1 fails to eliminate decode failures.
     */
    TWO,

    /**
     * Security Level 3 - If Security Level 2 is selected and decode failures still occur, select this security level. Be advised, selecting
     */
    THREE
}