package dk.gls.kdw.model.label

enum class LabelType {
    DATAMATRIX,
    I2OF5,
    CODE39,
    EAN13,
    UNKNOWN
}

fun LabelType.fromString(value: String) : LabelType {
    return when(value) {
        "LABEL-TYPE-DATAMATRIX" -> LabelType.DATAMATRIX
        "LABEL-TYPE-I2OF5" -> LabelType.I2OF5
        "LABEL-TYPE-CODE39" -> LabelType.CODE39
        "LABEL-TYPE-EAN13" -> LabelType.EAN13
        else -> LabelType.UNKNOWN
    }
}
