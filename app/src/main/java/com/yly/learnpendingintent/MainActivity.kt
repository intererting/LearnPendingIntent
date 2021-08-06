package com.yly.learnpendingintent

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat

@RequiresApi(Build.VERSION_CODES.O)
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        testNotification()
//        startOtherApk()
        startOtherApkWithPendingIntent()
//        testNotificationFlag()
    }

    private fun startOtherApkWithPendingIntent() {
        findViewById<Button>(R.id.startNotification).setOnClickListener {
            val componentName = ComponentName("com.yly.message", "com.yly.message.MessageActivity")

            val pendingIntent = PendingIntent.getActivity(
                applicationContext, 1001, Intent(applicationContext, IntentResult2Activity::class.java).apply {
                    putExtra("name", "yuliyang")
                },
                PendingIntent.FLAG_UPDATE_CURRENT
            )

            val otherApkIntent = Intent().apply {
                component = componentName
                addCategory(Intent.CATEGORY_DEFAULT)
                putExtra("pendingIntent", pendingIntent)
            }


            val resolveInfo = packageManager.resolveActivity(otherApkIntent, PackageManager.MATCH_DEFAULT_ONLY)
            if (resolveInfo != null) {
                startActivity(otherApkIntent)
            }
        }
    }

    private fun startOtherApk() {
        findViewById<Button>(R.id.startNotification).setOnClickListener {
            val componentName = ComponentName("com.yly.message", "com.yly.message.MessageActivity")
            val intent = Intent().apply {
                component = componentName
                addCategory(Intent.CATEGORY_DEFAULT)
            }
            val resolveInfo = packageManager.resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY)
            if (resolveInfo != null) {
                startActivity(intent)
            }
        }
    }

    private fun testNotification() {
        findViewById<Button>(R.id.startNotification).setOnClickListener {
            val channelId = "test"
            val channelName = "测试"
            val channel = NotificationChannel(
                channelId, channelName,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            if (null == notificationManager.getNotificationChannel(channelId)) {
                notificationManager.createNotificationChannel(channel)
            }
            val intent = Intent(applicationContext, IntentResultActivity::class.java).apply {
                putExtra("name", "yuliyang")
//                setDataAndType(Uri.parse("file://yuliyang"), "video/*")
            }
            val pendingIntent = PendingIntent.getActivity(
                applicationContext,
                1001,
                intent,
                PendingIntent.FLAG_IMMUTABLE
            )
            val builder = NotificationCompat.Builder(applicationContext, channelId)
                .setTicker("新消息来了")
                .setAutoCancel(true)
                .setContentText("点击打开新世界")
                .setContentTitle("标题")
                .setSmallIcon(android.R.drawable.ic_btn_speak_now)
                .setContentIntent(pendingIntent)
            notificationManager.notify(System.currentTimeMillis().toInt(), builder.build())
        }
    }

    /**
     * PendingIntent flag注释
     *
     * 1：flag要生效，那么requestCode和intent必须相同
     *2:cancel和update的时候只能设置extra，不能更改其他的
     *
     */
    private fun testNotificationFlag() {
        findViewById<Button>(R.id.startNotification2).setOnClickListener {
            val channelId = "test"
            val channelName = "测试"
            val channel = NotificationChannel(
                channelId, channelName,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            if (null == notificationManager.getNotificationChannel(channelId)) {
                notificationManager.createNotificationChannel(channel)
            }
            val intent = Intent(applicationContext, IntentResultActivity::class.java).apply {
                putExtra("name", "changed")
//                setDataAndType(Uri.parse("file://changed"), "image/*")
            }
            val pendingIntent = PendingIntent.getActivity(
                applicationContext,
                1001,
                intent,
                PendingIntent.FLAG_CANCEL_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
            val builder = NotificationCompat.Builder(applicationContext, channelId)
                .setTicker("新消息来了2")
                .setAutoCancel(true)
                .setContentText("点击打开新世界2")
                .setContentTitle("标题2")
                .setSmallIcon(android.R.drawable.ic_btn_speak_now)
                .setContentIntent(pendingIntent)
            notificationManager.notify(System.currentTimeMillis().toInt(), builder.build())
        }
    }
}