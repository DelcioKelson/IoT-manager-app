package com.manager.visitors

import android.app.Dialog
import android.os.Bundle
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.manager.databinding.DialogCreateUserBinding


class DialogCreateUser : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        // Inflate the layout for this fragment
        val inflater = requireActivity().layoutInflater
        val binding = DialogCreateUserBinding.inflate(inflater)
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.setContentView(binding.root)

        binding.btnClose.setOnClickListener { dismiss() }

        return dialog
    }

}