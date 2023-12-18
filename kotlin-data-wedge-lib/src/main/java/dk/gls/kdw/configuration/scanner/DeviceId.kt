package dk.gls.kdw.configuration.scanner

enum class DeviceId {
    /** Built-in imager scanner **/
    INTERNAL_IMAGER,
    /** Built-in laser scanner **/
    INTERNAL_LASER,
    /** Built-in camera scanner **/
    INTERNAL_CAMERA,
    /** Pluggable Z-back scanner for ET50/ET55 **/
    SERIAL_SSI,
    /** RS507 Bluetooth scanner **/
    BLUETOOTH_SSI,
    /** RS6000 Bluetooth scanner **/
    BLUETOOTH_RS6000,
    /** RS5100 Bluetooth scanner **/
    BLUETOOTH_RS5100,
    /** DS2278 Bluetooth scanner **/
    BLUETOOTH_DS2278,
    /** DS3678 Bluetooth scanner **/
    BLUETOOTH_DS3678,
    /** Serial SSI scanner RS429 (for use with WT6000) **/
    PLUGABLE_SSI,
    /** Serial SSI scanner RS5000 (for use with WT6000) **/
    PLUGABLE_SSI_RS5000,
    /** DS3608 pluggable USB scanner **/
    USB_SSI_DS3608
}