package com.manager.settings

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.manager.databinding.FragmentChangePasswordBinding
import com.manager.utils.UtilsFunctions

class FragmentChangePassword : Fragment() {

    private lateinit var binding: FragmentChangePasswordBinding

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View {
        binding = FragmentChangePasswordBinding.inflate(inflater, container, false)

        val user = FirebaseAuth.getInstance().currentUser

        binding.btnNext.setOnClickListener {

            if (!UtilsFunctions.isNetworkConnected(requireActivity())) {

                Toast.makeText(activity, "Erro de cenexão á internet", Toast.LENGTH_LONG).show()

            } else {

                if (isPasswordValid(binding.edtNew.text)) {
                    val credential = EmailAuthProvider.getCredential(
                            user!!.email.toString(),
                            binding.edtActual.text.toString()
                    )
                    user.reauthenticate(credential)
                            .addOnCompleteListener { task: Task<Void?> ->
                                if (task.isSuccessful) {
                                    if (binding.edtConfirmNew.text
                                            == binding.edtNew.text
                                    ) {
                                        user.updatePassword(binding.edtNew.text.toString())
                                                .addOnCompleteListener { task1: Task<Void?> ->
                                                    if (task1.isSuccessful) {
                                                        Toast.makeText(activity, "palavra passe alterarada com sucesso", Toast.LENGTH_LONG).show()
                                                        Navigation.findNavController(requireView())
                                                                .popBackStack()
                                                    }
                                                }
                                    } else {
                                        Toast.makeText(activity, "As palavra passes não coincidem", Toast.LENGTH_LONG).show()
                                    }
                                }
                            }.addOnFailureListener {
                                Toast.makeText(activity, "A palavra-passe actual errada", Toast.LENGTH_LONG).show()
                            }
                } else {
                    binding.edtNew.error = "palavra passe deve ter 6 digitos ou mais"
                }
            }
        }

        return binding.root
    }

    private fun isPasswordValid(text: Editable?): Boolean {
        return text!!.length >= 6
    }


}