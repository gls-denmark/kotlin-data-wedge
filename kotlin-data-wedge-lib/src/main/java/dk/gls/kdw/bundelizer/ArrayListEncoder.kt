package dk.gls.kdw.bundelizer

import android.os.Bundle
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.AbstractEncoder
import kotlinx.serialization.modules.SerializersModule

@OptIn(ExperimentalSerializationApi::class)
class ArrayListEncoder(
    private val arrayList: ArrayList<String>,
    private val parentBundle: Bundle? = null,
    private val keyInParent: String? = null,
    override val serializersModule: SerializersModule,
) : AbstractEncoder() {


    override fun endStructure(descriptor: SerialDescriptor) {
        parentBundle?.putStringArrayList(keyInParent, arrayList)
    }

    override fun encodeString(value: String) {
        arrayList.add(value)
    }
}