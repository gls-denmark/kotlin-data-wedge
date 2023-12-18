package dk.gls.kdw.configuration.model

import dk.gls.kdw.configuration.scanner.DeviceId

data class ScannerConnection(
    val id : String,
    val index : Int,
    val name : DeviceId,
    val connected : Boolean
)