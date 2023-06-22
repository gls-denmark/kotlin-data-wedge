package dk.gls.kdw.model.barcode.model

/**
 * Checks the integrity of the barcodes to verify that the data complies with a specified check digit algorithm.
 * Depending on which barcode format the check digit behaves differently. Refer to the documentation of the check digit on the specific barcode format
 */
enum class CheckDigit {
    /**
     * No check digit
     */
    NO,

    /**
     * One check digit
     */
    ONE,

    /**
     * Two check digit
     */
    TWO
}