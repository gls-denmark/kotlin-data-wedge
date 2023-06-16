# kotlin-data-wedge
The Zebra data wedge is utilised to configure and receive data from the Zebra hardware.

This library aims to make configuration of the data wedge easlier accessible and modernised through a type safe library api instead of the old Intent interface.

Consult the [Zebra Data wedge documentation here](https://techdocs.zebra.com/datawedge/13-0/guide/about/) in order to get more information about the capabilities of the Zebra DataWedge.

## KDW interface
The KDW interface is used to interact with the library.

The KDW interface has a configure method that receives a list of wished profile configurations.

The library currently support configuration of the following plugin configurations
- BarcodePluginConfiguration
- IntentPluginConfiguration

Be aware that the library does not provide any configuration on it's own. The library only changes the data wedge configuration if a given setting is set through the library. 
Consult the [DataWedge documentation](https://techdocs.zebra.com/datawedge/latest/guide/api/setconfig/) in order to see the zebra default values or inspect the resulting profile in the DataWedge app on the Zebra device. 

### BarcodePluginConfiguration
The barcode plugin configuration makes it possible to configure the barcode formats.

It takes a `BarcodeConfiguration` which has all available barcode formats and their specific settings available.
Provide the barcodes your app needs to recognize in the configuration and set barcodes you want to ignore to `enabled = false`.
 
A example BarcodeConfiguration plugin configuration

```
val barcodePluginConfiguration = BarcodePluginConfiguration(
    barcodeConfiguration = BarcodeConfiguration(
        australianPostal = AustralianPostal(enabled = false),
        code128 = Code128(
            enabled = true,
            length1 = 10,
            length2 = 20,
            redundancy = false,
            enabledEan128 = true,
            enabledIsbt128 = false,
            enablePlain = true,
            marginlessDecode = false,
            ignoreFnc4 = true
        )
    )
)
```

### IntentPluginConfiguration
In order to receive updates on the barcode scanner the `IntentPluginConfiguration` has to be provided to the configure method.

A example of a minimum intent configuration

```
val intentPluginConfiguration = IntentPluginConfiguration()
```

### AppConfiguration
The data wedge configuration are applied to your app based on a combination of the app package name and a list of activities in your app.
The KDW library uses `listOf(*)` as the default list of activities, meaning unless defined otherwise, the profile applies to all the apps activities.

A app configuration applying to all your apps activities would like to this 

```        
AppConfiguration(
    packageName = context.packageName
)
```

### ProfileConfiguration
The profile configuration combines the above configurations into a profile.
Besides giving the profile a name visible in the DataWedge profiles app, be sure to look at which `configMode` fits your application.

```
val profileConfiguration = ProfileConfiguration(
    enabled = true,
    name = "example profile",
    configMode = ConfigMode.CREATE_IF_NOT_EXIST,
    pluginConfigurations = arrayListOf(
        barcodePluginConfiguration,
        intentPluginConfiguration
    ),
    appList = arrayListOf(
        AppConfiguration(
            packageName = context.packageName
        )
    )
)
```

### KDW.Configure
Lastly call the configure method with your `ProfileConfiguration` in order for the KDW library to apply your profile providing a `context` in order for KDW
to broadcast the configuration in an intent to the `DataWedge`

```
KDW.configure(
    context = context,
    profileConfiguration = profileConfiguration
)
```

### ScannerController
The scanner controller makes it possible to `resumeScanner` and `suspendScanner` turning the scanner completely of, making the hardware buttons do nothing until resumed. 

Furthermore the `ScannerController` exposes the `scannerOutputFlow` which can be collected in order to receive updates on the current scanner state.

An example of a simple ViewModel that subscribe to the `scannerController` and interprets the output by logging it to the console

```
class ScannerControllerViewModel : ViewModel() {

    init {
        subscribeToScanner()
    }

    fun subscribeToScanner() = viewModelScope.launch {
        KDW.scannerController.scannerOutputFlow().map { output ->
            when (output) {
                is ScannerOutput.Result -> Log.i("ScannerControllerViewModel", "scannerOutputFlow result: ${output.scannerResult.symbology}")
                is ScannerOutput.Status -> Log.i("ScannerControllerViewModel", "scannerOutputFlow status: ${output.scannerStatus}")
            }
        }.collect()
    }
}
```

It is possible to have several subscribers on the `ScannerController` at the same time. 

#### Custom ScannerController
In case you want to have full control of how the scanner controller behaves you can provide a custom `ScannerController` to the `KDW.configure` method.
It makes it possible to add potential custom code before forwarding it to the the `dataWedgeHardwareScanner`.

An example of a custom scanner control that just forwards the resume and suspend commands but filters the `scannerOutputFlow()` to only emit `ScannerOutput.Result`
```
class CustomScannerController : ScannerController() {

    override fun resumeScanner() {
        dataWedgeHardwareScanner.resumeScanner()
    }

    override fun suspendScanner() {
        dataWedgeHardwareScanner.suspendScanner()
    }

    override fun scannerOutputFlow(): Flow<ScannerOutput.Result> {
        return dataWedgeHardwareScanner.scannerOutputFlow().filterIsInstance(ScannerOutput.Result::class)
    }
}
```