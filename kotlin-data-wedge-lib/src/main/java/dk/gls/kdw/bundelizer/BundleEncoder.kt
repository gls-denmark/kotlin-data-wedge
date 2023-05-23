package dk.gls.kdw.bundelizer

import android.os.Bundle
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.AbstractEncoder
import kotlinx.serialization.encoding.CompositeEncoder
import kotlinx.serialization.modules.SerializersModule

@ExperimentalSerializationApi
internal class BundleEncoder(
    private val bundle: Bundle,
    private val isInitializer: Boolean = true,
    override val serializersModule: SerializersModule,
) : AbstractEncoder() {

    private var key: String = ""

    override fun encodeElement(descriptor: SerialDescriptor, index: Int): Boolean {
        this.key = descriptor.getElementName(index)
        return super.encodeElement(descriptor, index)
    }

    override fun beginStructure(
        descriptor: SerialDescriptor
    ): CompositeEncoder {
        return if (isInitializer) {
            BundleEncoder(
                bundle = bundle,
                isInitializer = false,
                serializersModule = serializersModule,
            )
        } else {
            ArrayListEncoder(
                arrayList = arrayListOf(),
                parentBundle = bundle,
                keyInParent = key,
                serializersModule = serializersModule
            )
        }
    }

    override fun encodeBoolean(value: Boolean) {
        bundle.putBoolean(key, value)
    }

    override fun encodeByte(value: Byte) {
        bundle.putByte(key, value)
    }

    override fun encodeChar(value: Char) {
        bundle.putChar(key, value)
    }

    override fun encodeDouble(value: Double) {
        bundle.putDouble(key, value)
    }

    override fun encodeEnum(enumDescriptor: SerialDescriptor, index: Int) {
        bundle.putInt(key, index)
    }

    override fun encodeFloat(value: Float) {
        bundle.putFloat(key, value)
    }

    override fun encodeInt(value: Int) {
        bundle.putInt(key, value)
    }

    override fun encodeLong(value: Long) {
        bundle.putLong(key, value)
    }

    override fun encodeNull() {

    }

    override fun encodeShort(value: Short) {
        bundle.putShort(key, value)
    }

    override fun encodeString(value: String) {
        bundle.putString(key, value)
    }

    override fun encodeNotNullMark() {

    }
}