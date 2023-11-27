package dk.gls.kdw.configuration.scanner

import dk.gls.kdw.model.scanner.ScannerOutput
import kotlinx.coroutines.flow.Flow

interface HardwareScanner {
    fun scannerOutputFlow(): Flow<ScannerOutput>
    fun suspendScanner()
    fun resumeScanner()
    fun remoteScannerNotification(deviceId: DeviceId, notifications: List<RemoteScannerNotification>)
}