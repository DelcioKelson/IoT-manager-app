package com.manager.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.manager.databinding.FragmentSettingsBinding

class FragmentSettings : Fragment() {

    lateinit var binding: FragmentSettingsBinding

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSettingsBinding.inflate(inflater, container, false)

        val viewModelSettingsFactory = ViewModelSettingsFactory(this)
        val viewModelSettings = ViewModelProvider(this, viewModelSettingsFactory).get(
            ViewModelSettings::class.java)

        binding.lifecycleOwner = viewLifecycleOwner

        binding.settingsModel = viewModelSettings

        binding.user = Firebase.auth.currentUser!!

        return binding.root
    }
}