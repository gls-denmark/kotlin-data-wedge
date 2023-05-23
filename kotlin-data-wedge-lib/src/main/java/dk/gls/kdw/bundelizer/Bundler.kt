package dk.gls.kdw.bundelizer

import android.os.Bundle
import dk.gls.kdw.bundelizer.Bundler.defaultSerializersModule
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