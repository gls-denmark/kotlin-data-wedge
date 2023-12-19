# General (make debugging easier etc)
-dontobfuscate
-optimizations code/simplification/arithmetic,code/simplification/cast,field/*,class/merging/*
-optimizationpasses 5
-keepattributes SourceFile,LineNumberTable
-dontwarn java.lang.invoke.StringConcatFactory

# To support R8 we have some classes we exclude
-keepattributes *Annotation*, InnerClasses
-dontnote kotlinx.serialization.SerializationKt
-keep,includedescriptorclasses class dk.gls.kdw.**$$serializer { *; }

-keep class dk.gls.kdw.** {
    public private *;
}