package com.ransyadev.ssjattendance.utils.firebase

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.RingtoneManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.ransyadev.ssjattendance.R
import com.ransyadev.ssjattendance.ui.home.HomeActivity

class MyFirebaseMessagingService : FirebaseMessagingService() {
    override fun onNewToken(token: String) {
        sendNotifications(token, object : NotificationListener {
            override fun onNewToken(token: String) {
            }
        })
    }

    private fun sendNotifications(token: String, listener: NotificationListener) {
        listener.onNewToken(token)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        if (remoteMessage.data.keys.size > 0) {
            val title = remoteMessage.data["title"]
            val message = remoteMessage.data["body"]
            val type = remoteMessage.data["type"]
            showNotification(title ?: "", message ?: "", type ?: "")
        }
    }

    private fun showNotification(title: String, message: String, type: String) {
        val channelID = getString(R.string.default_notification_channel_id)
        val channelName = getString(R.string.default_notification_channel_name)

        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val description = "smart monitoring system apps"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelID, channelName, importance)
            channel.description = description
            channel.setShowBadge(true)
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            val notificationManager = getSystemService(
                NotificationManager::class.java
            )
            notificationManager?.createNotificationChannel(channel)
        }
        val intent  = Intent(this@MyFirebaseMessagingService, HomeActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)

        val largeIcon = BitmapFactory.decodeResource(
            resources,
            R.mipmap.ic_launcher
        )
        val soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val pattern = longArrayOf(0, 200, 60, 200)
        val builder: NotificationCompat.Builder = NotificationCompat.Builder(this, channelID)
            .setSmallIcon(R.drawable.ic_logo)
            .setBadgeIconType(NotificationCompat.BADGE_ICON_SMALL)
            .setLargeIcon(largeIcon)
            .setColor(ContextCompat.getColor(this, R.color.colorPrimary))
            .setContentTitle(title)
            .setStyle(NotificationCompat.BigTextStyle().bigText(message))
            .setContentText(message)
            .setAutoCancel(true)
            .setSound(soundUri)
            .setVibrate(pattern)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)

        val notificationManager = NotificationManagerCompat.from(this)
        val notificationId = (System.currentTimeMillis() / 4).toInt()
        notificationManager.notify(notificationId, builder.build())
    }

    companion object {
        const val TYPE_SUPPORT = "support"
        const val TYPE_HOME = "home"
    }


}
