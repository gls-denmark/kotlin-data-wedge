package dk.gls.kdw.model.barcode.matrix

import android.os.Bundle
import dk.gls.kdw.serilization.Bundlizer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DataMatrix(
    @SerialName("decoder_datamatrix")
    val enabled: Boolean = true
)

fun DataMatrix.toBundle(): Bundle = Bundlizer.bundle(DataMatrix.serializer(), this)