package com.example.worldoff1.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.worldoff1.model.Driver
import com.example.worldoff1.network.RetrofitInstance
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateOf

class DriverViewModel : ViewModel() {

    var driverList = mutableStateOf<List<Driver>>(emptyList())

    init {
        getDrivers()
    }

    private fun getDrivers() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getDrivers()
                driverList.value = response.MRData.DriverTable.Drivers
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}