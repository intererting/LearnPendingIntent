package com.yly.learnpendingintent

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

/**
 * @author    yiliyang
 * @date      2021/8/6 下午3:01
 * @version   1.0
 * @since     1.0
 */
class IntentResult2Activity : AppCompatActivity(R.layout.activity_intent_result) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        findViewById<TextView>(R.id.content).setText("IntentResult2Activity")
        println(intent.getStringExtra("name"))
        println(intent.getIntExtra("age", 0))
    }
}