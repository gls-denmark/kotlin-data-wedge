package dk.gls.kdw.model.barcode.model

/**
 * The scanner offers four levels of decode security for I2of5 barcodes. As the quality of barcodes decreases, implementing an increased level of security compensates and helps improve decoding success.
 * There is an inverse relationship between scanner aggressiveness and security. Zebra recommends to carefully choose the level of security necessary for any given application.
 */
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
     * Security Level 2 - Select this option if Security level 1 fails to eliminate decode failures.
     */
    TWO,

    /**
     * Security Level 3 - If Security Level 2 is selected and decode failures still occur, select this security level. Be advised, selecting this option is an extreme measure against mis-decoding severely out-of-spec barcodes.
     * Selecting this security level significantly impairs the decoding ability of the scanner. If this level of security is required, try to improve the quality of the barcodes.
     */
    THREE
}