package dk.gls.kdw.model.label

/**
 * A convenience enum class containing known LabelType's
 * Use the [String.toLabelType] function in order to interpret the incoming [ScannerResult] symbology as a [LabelType]
 */
enum class LabelType {
    DATAMATRIX,
    I2OF5,
    CODE39,
    EAN13,
    UNKNOWN
}

/**
 * Converts the received [ScannerResult] symbology string into a [LabelType]
 */
fun String?.toLabelType() : LabelType {
    return when(this) {
        "LABEL-TYPE-DATAMATRIX" -> LabelType.DATAMATRIX
        "LABEL-TYPE-I2OF5" -> LabelType.I2OF5
        "LABEL-TYPE-CODE39" -> LabelType.CODE39
        "LABEL-TYPE-EAN13" -> LabelType.EAN13
        else -> LabelType.UNKNOWN
    }
}
