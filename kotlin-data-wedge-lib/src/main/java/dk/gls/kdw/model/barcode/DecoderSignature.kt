package dk.gls.kdw.model.barcode

import android.os.Bundle
import dk.gls.kdw.model.barcode.model.BitsPerPixel
import dk.gls.kdw.model.barcode.model.SignatureFormat
import kotlinx.serialization.SerialName

/**
 * Decoder Signature, also known as Signature Capture, is a special barcode format that captures an area of a document (such as a signature) as an image. It is marked by two identical bar patterns placed on either side of the capture area. The bar pattern extends the full height of that area.
 * For help creating a Signature Capture barcode like the one above, refer to the Decoder Signature Guide.
 */
data class DecoderSignature(
    val enabled: Boolean = true,

    /**
     * Specify the desired output image format - JPEG, BMP, TIFF.
     */
    val format: SignatureFormat,
    /**
     * Specify the desired output image width in number of pixels. The aspect ratio of Width to Height must match the aspect ratio of the signature capture barcode to avoid distortion in the captured image.
     * Integer from 16-1280
     * Default = 400
     */
    val width: Int = 400,

    /**
     * Specify the desired output image height in number of pixels. The aspect ratio of Width to Height must match the aspect ratio of the signature capture barcode to avoid distortion in the captured image.
     * Integer from 16-800
     * Default = 400
     */
    val height: Int = 400,
    /**
     * An integer value between 5 and 100 with increments of 5, with 100 representing the highest quality image and 5 representing the most optimized for image size.
     * Integer from 5-100 in increments of 5
     * Default = 65
     */
    val jpegQuality: Int = 65,

    /**
     * Used to select the number of significant bits per pixel (BPP) to use when capturing a signature
     */
    val bitsPerPixel: BitsPerPixel
)

fun DecoderSignature.toBundle(): Bundle {
    return Bundle().apply {
        this.putBoolean("decoder_signature", enabled)
        this.putInt("decoder_signature_format", format.value)
        this.putInt("decoder_signature_width", width)
        this.putInt("decoder_signature_height", height)
        this.putInt("decoder_signature_jpegquality", jpegQuality)
        this.putInt("decoder_signature_bpp", bitsPerPixel.ordinal)
    }
}