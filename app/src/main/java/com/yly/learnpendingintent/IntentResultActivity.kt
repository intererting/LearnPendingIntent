package com.yly.learnpendingintent

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * @author    yiliyang
 * @date      2021/8/6 下午3:01
 * @version   1.0
 * @since     1.0
 */
class IntentResultActivity : AppCompatActivity(R.layout.activity_intent_result) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        intent.apply {
            println(intent.getStringExtra("name"))
        }
    }
}