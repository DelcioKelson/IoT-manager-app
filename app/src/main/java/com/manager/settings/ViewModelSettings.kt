package com.manager.settings

import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.manager.MainActivity
import com.manager.R

class ViewModelSettings(val fragment: FragmentSettings) : ViewModel() {

    private var fSelectedUri: Uri? = null
    private var user: FirebaseUser = Firebase.auth.currentUser!!
    var photoProfile: MutableLiveData<Uri> = MutableLiveData(null)

    fun onClickChangePasswordFragment(v: View?) {
        Navigation.findNavController(v!!)
                .navigate(R.id.action_fragment_settings_to_fragment_change_password)
    }

    fun onClickSignOut() {
        Firebase.auth.signOut()
        val intent = Intent(fragment.context, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        fragment.startActivity(intent)
    }

    fun onClickSavePhoto(view: View) {
        view.visibility = View.GONE
        val builder = UserProfileChangeRequest.Builder()
        builder.photoUri = fSelectedUri
        val changeRequest = builder.build()
        user.updateProfile(changeRequest)
    }

    private val getContent =
            fragment.registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
                fSelectedUri = uri
                if (uri != null) {
                    photoProfile.value = fSelectedUri

                }
            }

    fun onClickOpenImageSelector() {
        getContent.launch("image/*")
    }
}