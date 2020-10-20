package com.harbinger.qademo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.harbinger.qademo.business.display.DisplayActivity
import com.harbinger.qademo.business.InteractionActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUI()
    }

    private fun initUI() {
        display_port_btn.setOnClickListener {
            val intent = Intent(this, DisplayActivity::class.java)
            startActivity(intent)
        }
        interaction_port_btn.setOnClickListener {
            val intent = Intent(this, InteractionActivity::class.java)
            startActivity(intent)
        }
    }
}