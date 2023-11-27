# General (make debugging easier etc)
-dontobfuscate
-optimizations code/simplification/arithmetic,code/simplification/cast,field/*,class/merging/*
-optimizationpasses 5
-keepattributes SourceFile,LineNumberTable
-dontwarn java.lang.invoke.StringConcatFactory

# To support R8 we have some classes we exclude from
-keepattributes *Annotation*, InnerClasses
-dontnote kotlinx.serialization.SerializationKt
-keep,includedescriptorclasses class dk.gls.kdw.**$$serializer { *; }
-keepclassmembers class dk.gls.kdw.** {
    *** Companion;
}

-keepclasseswithmembers class dk.gls.kdw.** {
    kotlinx.serialization.KSerializer serializer(...);
}

-keep class dk.gls.kdw.**$DefaultImpls {
    *;
}

-keep class dk.gls.kdw.** {
     *;
}

-keep class dk.gls.kdw.configuration.** {
    *;
}

-keep class dk.gls.kdw.configuration.model.** {
    *;
}

-keep class dk.gls.kdw.configuration.plugin.** {
    *;
}

-keep class dk.gls.kdw.configuration.scanner.** {
    *;
}

-keep class dk.gls.kdw.model.** {
    *;
}

-keep class dk.gls.kdw.model.barcode.** {
    *;
}

-keep class dk.gls.kdw.model.intent.** {
    *;
}

-keep class dk.gls.kdw.model.label.** {
    *;
}

-keep class dk.gls.kdw.model.scanner.** {
    *;
}

-keep class dk.gls.kdw.configuration.scanner.ParityFlowScannerController {
    *;
}

-keep class dk.gls.kdw.configuration.scanner.DeviceId {
    *;
}

-keep class dk.gls.kdw.configuration.scanner.RemoteScannerNotification {
    *;
}