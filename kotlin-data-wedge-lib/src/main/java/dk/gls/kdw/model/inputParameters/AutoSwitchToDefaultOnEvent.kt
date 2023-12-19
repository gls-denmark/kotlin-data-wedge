package dk.gls.kdw.model.inputParameters

/**
 * This feature configures DataWedge to select an external scanner as the default scanning device immediately upon connection and revert to a built-in scanner when the external scanner is disconnected.
 * External scanners include those connecting by Bluetooth, serial cable or snap-on module. If enabled, SWITCH_SCANNER should not be used upon scanner connection/disconnection as it can cause unexpected behavior.
 * Disabled by default. Available only when “Auto" is selected in the Scanner selection panel.
 *
 * This feature is intended to help reduce scanning workflow interruptions when a Bluetooth scanner is introduced and/or it becomes disconnected by losing power or moving out of range.
 *
 * For Bluetooth scanners, if the device was not previously paired, a pairing barcode is displayed prior to automatic connection.
 */
enum class AutoSwitchToDefaultOnEvent(val value: Int) {
    /**
     * No scanner switching occurs when an external scanner is connected or disconnected (default)
    **/
    DISABLED(0),
    /**
     * Selects the external scanner as the default scanning device immediately upon connection.
     **/
    ON_CONNECT(1),
    /**
     * Reverts to a built-in scanner based on its position in an internally managed scanner list (which varies by host device). This is usually the scanner most recently used prior to the external connection (see notes below).
     **/
    ON_DISCONNECT(2),
    /**
     * Selects an external scanner as the default scanning device immediately upon connection. Upon disconnection, reverts to the scanner set as the default prior to the external connection.
     **/
    ON_CONNECT_DISCONNECT(3)
}