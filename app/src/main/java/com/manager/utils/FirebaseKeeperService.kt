package com.manager.utils

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import com.manager.visitors.ClassUser
import com.manager.visitors.ClassUser.Companion.toUser

object FirebaseKeeperService {
    private const val TAG = "FirebaseGymService"

    suspend fun getUserData(): List<ClassUser> {
        val db = FirebaseFirestore.getInstance()
        return try {
            db.collection("users")
                    .get().await()
                    // mapNotNull helps to avoid incompatible documents
                    .documents.mapNotNull { it.toUser() }
        } catch (e: Exception) {
            Log.e(TAG, "Error getting gym details", e)
            emptyList()
        }
    }

}