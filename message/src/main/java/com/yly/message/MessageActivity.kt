package com.yly.message

import android.app.PendingIntent
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MessageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message)
        findViewById<Button>(R.id.start).setOnClickListener {
            val pendingIntent = intent.getParcelableExtra<PendingIntent>("pendingIntent")
            //如果pendingIntent是可变的，那尝试修改
            val changeIntent = Intent().apply {
                //特别注意，这个地方只能添加不能修改
                putExtra("name", "changed")//错误
                putExtra("age", 10)
            }
            pendingIntent?.send(applicationContext, 1001, changeIntent)
        }
    }
}