package com.harbinger.qademo.business

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.harbinger.qademo.R
import com.harbinger.qademo.websocket.SocketManager
import kotlinx.android.synthetic.main.activity_interaction.*

/**
 * Created by acorn on 2020/10/19.
 */
class InteractionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_interaction)
        initUI()
    }

    private fun initUI() {
        connect_btn.setOnClickListener {
            if (url_et.text.isEmpty()) {
                Toast.makeText(this, "请先输入URL", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            SocketManager.url = url_et.text.toString()
            connect_btn.text = "已连接"
        }
        send_btn.setOnClickListener {
            if (message_et.text.isEmpty()) {
                Toast.makeText(this, "请先输入message", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            SocketManager.instance.send(message_et.text.toString())
            Toast.makeText(this, "已发送", Toast.LENGTH_SHORT).show()
        }
    }
}