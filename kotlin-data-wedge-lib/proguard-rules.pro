# General (make debugging easier etc)
-dontobfuscate
-optimizations code/simplification/arithmetic,code/simplification/cast,field/*,class/merging/*
-optimizationpasses 5
-keepattributes SourceFile,LineNumberTable

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

-keep class dk.gls.kdw.KDW {
    *;
}

-keep class dk.gls.kdw.configuration.scanner.ParityFlowScannerController {
    *;
}
