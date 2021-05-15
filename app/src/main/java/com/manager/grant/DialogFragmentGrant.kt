package com.manager.grant

import android.annotation.SuppressLint
import android.app.Dialog
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.DialogFragment
import com.google.firebase.firestore.FirebaseFirestore
import com.manager.MainActivity
import com.manager.R
import com.manager.databinding.DialogFragmentGrantBinding

class DialogFragmentGrant(val idUser:String) : DialogFragment() {
    private lateinit var binding: DialogFragmentGrantBinding
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        // Inflate the layout for this fragment
        val inflater = requireActivity().layoutInflater
        binding = DialogFragmentGrantBinding.inflate(inflater)
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.setContentView(binding.root)
        binding.btnClose.setOnClickListener { dismiss()}
        binding.btnRefuse.setOnClickListener { dismiss()}

        binding.btnAllow.setOnClickListener{
            FirebaseFirestore.getInstance().collection("visotors").document(idUser)
                .update("allow", "yes")
            startTimer()
        }

        return dialog
    }

    private fun startTimer() {

        object : CountDownTimer(5000, 1000) {
            @SuppressLint("SetTextI18n")
            override fun onTick(millisUntilFinished: Long) {
            }

            override fun onFinish() {
                val intent = Intent(requireContext(), MainActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                }
                val pendingIntent: PendingIntent = PendingIntent.getActivity(requireContext(), 0, intent, 0)

                createNotificationChannel()
                requireActivity().applicationContext.let {
                    val builder = NotificationCompat.Builder(it, "3000")
                        .setSmallIcon(R.drawable.ic_check_24)
                        .setContentTitle("Porta trancada")
                        .setChannelId("myChannel")
                        .setContentText("A porta foi trancada com sucesso")
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setContentIntent(pendingIntent)
                    NotificationManagerCompat.from(it).notify(0, builder.build())
                }
            }
        }.start()
    }
    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        val descriptionText = "R.string.channel_description"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel("myChannel", "myChannel", importance).apply {
            description = descriptionText
        }
        // Register the channel with the system
        val notificationManager: NotificationManager =
            requireContext().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}