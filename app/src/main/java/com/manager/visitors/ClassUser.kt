package com.manager.visitors

import android.os.Parcelable
import android.util.Log
import com.google.firebase.firestore.DocumentSnapshot
import kotlinx.parcelize.Parcelize

@Parcelize
data class ClassUser(val name: String,  val id: String) : Parcelable {

    companion object {

        fun DocumentSnapshot.toUser(): ClassUser? {
            return try {
                val name = getString("name")!!
                ClassUser(name, id)
            } catch (e: Exception) {
                Log.e(TAG, "Error converting user profile", e)
                null
            }
        }

        private const val TAG = "Gym"
    }
}

