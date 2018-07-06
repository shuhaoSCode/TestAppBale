package com.l.testappbale

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.l.getchannel.ManifestUtil
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var channel = ""
        //获取channel
        channel = ManifestUtil.getChannel(this)
        text.setText(channel)
    }
}
