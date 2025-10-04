package com.pgzxc.splashscreendemo

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.VideoView
import androidx.core.app.ComponentActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

@SuppressLint("RestrictedApi")
class SplashVideoActivity : ComponentActivity() {
    @SuppressLint("MissingSuperCall")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 安装启动画面，快速过渡
        installSplashScreen().setKeepOnScreenCondition { false }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_video)

        // 获取 VideoView
        val videoView = findViewById<VideoView>(R.id.video_view)
        // 设置视频路径 (res/raw/splash_video.mp4)
        val videoUri = Uri.parse("android.resource://${packageName}/${R.raw.splash_video}")
        videoView.setVideoURI(videoUri)

        // 视频播放完成时跳转到主 Activity
        videoView.setOnCompletionListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        // 错误处理
        videoView.setOnErrorListener { _, what, extra ->
            // 如果视频播放失败，直接跳转到主 Activity
            startActivity(Intent(this, MainActivity::class.java))
            finish()
            true // 表示错误已处理
        }

        // 开始播放
        videoView.start()

    }

    private fun before() {
        val videoView = findViewById<VideoView>(R.id.video_view)
        //videoView.setVideoURI(Uri.parse("android.resource://$packageName/raw/splash_video"))
        val videoUri = Uri.parse("android.resource://${packageName}/${R.raw.splash_video}")
        videoView.setVideoURI(videoUri)
        videoView.setOnCompletionListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        videoView.start()
    }
}