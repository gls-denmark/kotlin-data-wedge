package dk.gls.kdw.model.barcode.code

import android.os.Bundle
import dk.gls.kdw.bundler.Bundler
import dk.gls.kdw.model.barcode.model.CheckDigit
import dk.gls.kdw.model.barcode.model.ConcatMode
import dk.gls.kdw.model.barcode.model.SecurityLevel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Code128(
    @SerialName("decoder_code128")
    val enabled: Boolean = true,
    /**
     * 	Sets the lower limit when decoding a barcode with a specific length range. For example, to decode a barcode containing between 4 and 12 characters, set this value to 4.
     */
    @SerialName("decoder_code128_length1")
    val length1: Int,
    /**
     * 	Sets the upper limit when decoding a barcode with a specific length range. For example, to decode a barcode containing between 4 and 12 characters, set this value to 12.
     */
    @SerialName("decoder_code128_length2")
    val length2: Int,
    /**
     * Sets the reader to read the barcode twice before accepting data.
     */
    @SerialName("decoder_code128_redundancy")
    val redundancy: Boolean,
    /**
     * Enable GS1-128
     */
    @SerialName("decoder_code128_enable_ean128")
    val enabledEan128: CheckDigit,
    /**
     * Enable ISBT128
     */
    @SerialName("decoder_code128_enable_isbt128")
    val enabledIsbt128: Boolean,
    /**
     * Enables other (non-EAN or ISBT) Code 128 subtypes.
     */
    @SerialName("decoder_code128_enable_plain")
    val enablePlain: Boolean,
    /**
     * Select an option for concatenating pairs of ISBT code types
     */
    @SerialName("decoder_code128_isbt128_concat_mode")
    val convertToCode32: ConcatMode = ConcatMode.NEVER,
    /**
     * The ISBT specification includes a table that lists several types of ISBT barcodes that are commonly used in pairs. If ISBT128 Concat Mode is set, enable "Check ISBT Table" to concatenate only those pairs found in this table. Other types of ISBT codes are not concatenated.
     */
    @SerialName("decoder_code128_check_isbt_table")
    val checkIsbtTable: Boolean = false,
    /**
     * There are four levels of decode security for Code 128 barcodes. As the quality of barcodes decreases, implementing an increased level of security compensates and helps improve decoding success. There is an inverse relationship between scanner aggressiveness and security. Zebra recommends choosing carefully the level of security necessary for any given application
     */
    @SerialName("decoder_code128_security_level")
    val securityLevel: SecurityLevel = SecurityLevel.ZERO,
    /**
     * Specifies the decode security for Code 39 barcodes
     */
    @SerialName("code128_enable_marginless_decode")
    val marginlessDecode: Boolean,
    /**
     * 	When enabled and a Code 128 barcode has an embedded FNC4 character, it is removed from the data and the following characters are not changed. When the feature is disabled, the FNC4 character is not transmitted but the following character will have 128 added to it.
     */
    @SerialName("code128_ignore_fnc4")
    val ignoreFnc4: Boolean
)

fun Code128.toBundle(): Bundle = Bundler.bundle(Code128.serializer(), this)