package dk.gls.kdw.model.barcode.model

enum class BitsPerPixel {
    /**
     * 1 BPP - For a black and white image.
     */
    ONE,

    /**
     * 4 BPP - Assigns 1 of 16 levels of grey to each pixel.
     */
    FOUR,

    /**
     * 8 BPP - Assigns 1 of 256 levels of grey to each pixel.
     */
    EIGHT
}