package com.commandhub.mediahub.services

import android.content.Context
import android.net.nsd.NsdManager
import android.net.nsd.NsdServiceInfo
import android.util.Log

class NetworkServiceDiscovery(
    context: Context,
    private val onServerFound: (NsdServiceInfo) -> Unit,
    private val onServerLost: (NsdServiceInfo) -> Unit
) {
    private val nsdManager = context.getSystemService(Context.NSD_SERVICE) as NsdManager
    private val serviceName = "Media Server"
    private val serviceType = "_http._tcp."

    private val discoveryListener = object : NsdManager.DiscoveryListener {

        override fun onDiscoveryStarted(regType: String) {
            Log.d("NSD", "Service discovery started")
        }

        override fun onServiceFound(service: NsdServiceInfo) {
            Log.d("NSD", "Service discovery success $service")
            if (service.serviceType == serviceType && service.serviceName.contains(serviceName)) {
                Log.d("NSD", "Found correct service: ${service.serviceName}")

                nsdManager.resolveService(service, object : NsdManager.ResolveListener {
                    override fun onResolveFailed(serviceInfo: NsdServiceInfo, errorCode: Int) {
                        Log.e("NSD", "Resolve failed: $errorCode")
                    }

                    override fun onServiceResolved(serviceInfo: NsdServiceInfo) {
                        onServerFound(serviceInfo)
                    }
                })
            }
        }

        override fun onServiceLost(service: NsdServiceInfo) {
            onServerLost(service)
        }

        override fun onDiscoveryStopped(serviceType: String) {
            Log.i("NSD", "Discovery stopped: $serviceType")
        }

        override fun onStartDiscoveryFailed(serviceType: String, errorCode: Int) {
            Log.e("NSD", "Discovery start failed: Error code:$errorCode")
            nsdManager.stopServiceDiscovery(this)
        }

        override fun onStopDiscoveryFailed(serviceType: String, errorCode: Int) {
            Log.e("NSD", "Discovery stop failed: Error code:$errorCode")
            nsdManager.stopServiceDiscovery(this)
        }
    }

    fun discoverServices() {
        nsdManager.discoverServices(serviceType, NsdManager.PROTOCOL_DNS_SD, discoveryListener)
    }

    fun stopDiscovery() {
        nsdManager.stopServiceDiscovery(discoveryListener)
    }
}