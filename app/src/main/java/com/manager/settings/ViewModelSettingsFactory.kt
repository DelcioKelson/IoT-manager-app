package com.manager.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelSettingsFactory(private val fragment: FragmentSettings) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ViewModelSettings::class.java)) {
            return ViewModelSettings(fragment) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}