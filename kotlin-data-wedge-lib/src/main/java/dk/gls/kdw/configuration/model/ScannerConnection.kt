package dk.gls.kdw.configuration.model

import dk.gls.kdw.configuration.scanner.DeviceId

data class ScannerConnection(
    val id : DeviceId,
    val index : Int,
    val name : String,
    val connected : Boolean
)