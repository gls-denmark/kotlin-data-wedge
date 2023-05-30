package dk.gls.kdw.model.barcode.model

enum class UCCLinkMode {
    /**
     * UPC Never Linked - Transmit UPC barcodes regardless of whether a 2D barcode is detected.
     */
    LINK_FLAG_IGNORED,

    /**
     * UPC Always Linked - Transmit UPC barcodes and the 2D portion. If 2D is not present, do not transmit the barcode.
     */
    ALWAYS_LINKED,

    /**
     * Autodiscriminate UPC Composites - The scanner determines if there is a 2D portion, then transmits the UPC, as well as the 2D portion if present.
     */
    AUTO_DISCRIMINATE,
}