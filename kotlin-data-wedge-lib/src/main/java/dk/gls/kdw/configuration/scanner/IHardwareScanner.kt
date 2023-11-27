package dk.gls.kdw.configuration.scanner

import dk.gls.kdw.model.scanner.ScannerOutput
import kotlinx.coroutines.flow.Flow

interface IHardwareScanner {
    /**
     * Receive the [ScannerOutput] as a flow
     */
    fun scannerOutputFlow(): Flow<ScannerOutput>

    /** Suspend the scanner **/
    fun suspendScanner()

    /** Resume the scanner **/
    fun resumeScanner()

    /**
     * Send a variable amount of notification lists specifying the notifications for the remote scanner (RMS)
     * The notifications are send with a delay between parsed lists of 1000 ms
     * Official documentation
     * @link https://techdocs.zebra.com/datawedge/8-2/guide/api/notify/
     **/
    fun remoteScannerNotifications(deviceId: DeviceId, vararg notifications: List<RemoteScannerNotification>)
}