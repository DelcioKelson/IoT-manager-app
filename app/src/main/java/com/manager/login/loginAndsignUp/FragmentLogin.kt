package com.manager.login.loginAndsignUp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.manager.databinding.FragmentLoginBinding

class FragmentLogin : Fragment() {
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)


        val viewModelLoginFactory = ViewModelLoginFactory(this)
        val viewModelLogin = ViewModelProvider(this, viewModelLoginFactory).get(ViewModelLogin::class.java)
        binding.loginViewModel = viewModelLogin


        return binding.root
    }

}