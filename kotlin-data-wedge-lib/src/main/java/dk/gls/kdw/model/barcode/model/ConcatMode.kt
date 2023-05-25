package dk.gls.kdw.model.barcode.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Select an option for concatenating pairs of ISBT code types
 */
@Serializable
enum class ConcatMode {
    /**
     * Concat Mode Never - Do not concatenate pairs of ISBT codes encountered.
     */
    @SerialName("0")
    NEVER,

    /**
     * Concat Mode Always - There must be two ISBT codes in order to decode and perform concatenation. Does not decode single ISBT barcodes.
     */
    @SerialName("1")
    ALWAYS,

    /**
     * Concat Mode Auto - Decodes and concatenates pairs of ISBT codes immediately. If only a single ISBT barcode is present, the device must decode the barcode the number of times set via Redundancy - Code128 before transmitting its data to confirm that there is no additional ISBT barcode.
     */
    @SerialName("2")
    AUTO
}