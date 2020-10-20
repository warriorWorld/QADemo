package com.harbinger.qademo.business

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.harbinger.qademo.R
import com.harbinger.qademo.listener.MessageListener
import com.harbinger.qademo.utils.NetUtils
import com.harbinger.qademo.websocket.MyWebSocketServer
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.net.InetAddress
import java.net.InetSocketAddress
import kotlinx.android.synthetic.main.activity_display.*

/**
 * Created by acorn on 2020/10/19.
 */
class DisplayActivity : AppCompatActivity() {
    private val TAG = "DisplayActivity"
    private var server: MyWebSocketServer? = null
    private var disposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)
        initUI()
    }

    private fun initUI() {
        start_btn.setOnClickListener {
            val port = 43496
            disposable = Observable.create<InetAddress> {
                it.onNext(NetUtils.getLocalIPAddress())
                it.onComplete()
            }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    server = MyWebSocketServer(InetSocketAddress(it, port))
                    server?.messageListener = object : MessageListener {
                        override fun onMessage(msg: String?) {
                            runOnUiThread { result_tv.text = msg }
                        }
                    }
                    server?.start()

//                    Log.d(TAG, "ws:${it.hostAddress}:$port")
                    println("ws:${it.hostAddress}:$port")
                    start_btn.text = "已连接：ws:${it.hostAddress}:$port"
                }
        }
        stop_btn.setOnClickListener {
            server?.stop()
            start_btn.text = "开始"
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable?.dispose()
    }
}