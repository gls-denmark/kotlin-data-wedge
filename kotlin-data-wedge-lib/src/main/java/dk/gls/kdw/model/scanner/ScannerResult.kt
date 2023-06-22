package dk.gls.kdw.model.scanner

import dk.gls.kdw.model.label.LabelType

/**
 * Scanner result. A string of received data and a symbology [String.toScannerStatusEnum].
 * [symbology] can be interpreted into a [LabelType] using String.toLabelType()
 */
data class ScannerResult(
    val data: String?,
    val symbology: String?
)