package dk.gls.kdw.model.barcode

import dk.gls.kdw.model.barcode.model.BitsPerPixel
import dk.gls.kdw.model.barcode.model.SignatureFormat
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Decoder Signature, also known as Signature Capture, is a special barcode format that captures an area of a document (such as a signature) as an image. It is marked by two identical bar patterns placed on either side of the capture area. The bar pattern extends the full height of that area.
 * For help creating a Signature Capture barcode like the one above, refer to the Decoder Signature Guide.
 */
@Serializable
data class DecoderSignature(
    @SerialName("decoder_signature")
    val enabled: Boolean = true,

    /**
     * Specify the desired output image format - JPEG, BMP, TIFF.
     */
    @SerialName("decoder_signature_format")
    val format: SignatureFormat,
    /**
     * Specify the desired output image width in number of pixels. The aspect ratio of Width to Height must match the aspect ratio of the signature capture barcode to avoid distortion in the captured image.
     * Integer from 16-1280
     * Default = 400
     */
    @SerialName("decoder_signature_width")
    val width: Int = 400,

    /**
     * Specify the desired output image height in number of pixels. The aspect ratio of Width to Height must match the aspect ratio of the signature capture barcode to avoid distortion in the captured image.
     * Integer from 16-800
     * Default = 400
     */
    @SerialName("decoder_signature_height")
    val height: Int = 400,
    /**
     * An integer value between 5 and 100 with increments of 5, with 100 representing the highest quality image and 5 representing the most optimized for image size.
     * Integer from 5-100 in increments of 5
     * Default = 65
     */
    @SerialName("decoder_signature_jpegquality")
    val jpegQuality: Int = 65,

    /**
     * Used to select the number of significant bits per pixel (BPP) to use when capturing a signature
     */
    @SerialName("decoder_signature_bpp")
    val bitsPerPixel: BitsPerPixel
)