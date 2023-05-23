package dk.gls.kdw.bundler

import android.os.Bundle
import dk.gls.kdw.bundler.Bundler.defaultSerializersModule
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.modules.EmptySerializersModule
import kotlinx.serialization.modules.SerializersModule

/**
 * Utility class for the Bundlizer library
 */
object Bundler {

    var defaultSerializersModule: SerializersModule = EmptySerializersModule
        set(value) {
            synchronized(Bundler) { field = value }
        }

    /**
     * Deserialize this bundle into an object of type T
     *
     * @param deserializer DeserializationStrategy of the T class
     * @param bundle bundle to deserialize
     * @return object of type T deserialized from bundle
     */
    @JvmStatic
    fun <T> unbundle(
        deserializer: DeserializationStrategy<T>,
        bundle: Bundle,
        serializersModule: SerializersModule = defaultSerializersModule,
    ): T {
        val decoder = BundleDecoder(
            bundle = bundle,
            elementsCount = -1,
            isInitializer = true,
            serializersModule = serializersModule,
        )
        return deserializer.deserialize(decoder)
    }

    /**
     * Serialize this object into a bundle
     *
     * @param serializer SerializationStrategy of the T class
     * @param value value to serialize
     * @return bundle serialized from value
     */
    @JvmStatic
    fun <T> bundle(
        serializer: SerializationStrategy<T>,
        value: T,
        serializersModule: SerializersModule = defaultSerializersModule,
    ): Bundle {
        val bundle = Bundle(serializer.descriptor.elementsCount)
        val encoder = BundleEncoder(
            bundle = bundle,
            isInitializer = true,
            serializersModule = serializersModule,
        )
        serializer.serialize(encoder, value)
        return bundle
    }
}

/**
 * Deserialize this bundle into an object of type T
 *
 * @receiver bundle to deserialize
 * @param deserializer DeserializationStrategy of the T class
 * @return object of type T deserialized from bundle
 */
fun <T> Bundle.unbundle(
    deserializer: DeserializationStrategy<T>,
    serializersModule: SerializersModule = defaultSerializersModule,
): T {
    return Bundler.unbundle(deserializer, this, serializersModule)
}

/**
 * Serialize this object into a bundle
 *
 * @receiver value to serialize
 * @param serializer SerializationStrategy of the T class
 * @return bundle serialized from value
 */
fun <T> T.bundle(
    serializer: SerializationStrategy<T>,
    serializersModule: SerializersModule = defaultSerializersModule,
): Bundle {
    return Bundler.bundle(serializer, this, serializersModule)
}