package com.commandhub.mediahub.models

import android.app.Application
import android.net.nsd.NsdServiceInfo
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import com.commandhub.mediahub.services.NetworkServiceDiscovery

class NsdViewModel(application: Application) : AndroidViewModel(application) {
    private val _services = mutableStateListOf<NsdServiceInfo>()
    val services: List<NsdServiceInfo> = _services

    private var nsdHelper: NetworkServiceDiscovery? = null

    init {
        nsdHelper = NetworkServiceDiscovery(
            getApplication<Application>().applicationContext,
            onServerFound = { addService(it) },
            onServerLost = { removeService(it) }
        )
    }

    private fun addService(service: NsdServiceInfo) {
        if (!_services.any { it.serviceName == service.serviceName }) {
            _services.add(service)
        }
    }

    private fun removeService(service: NsdServiceInfo) {
        _services.remove(service)
    }

    fun startDiscovery() {
        _services.clear()
        nsdHelper?.discoverServices()
    }

    fun stopDiscovery() {
        nsdHelper?.stopDiscovery()
    }

    override fun onCleared() {
        super.onCleared()
        nsdHelper?.stopDiscovery()
        nsdHelper = null
    }
}

