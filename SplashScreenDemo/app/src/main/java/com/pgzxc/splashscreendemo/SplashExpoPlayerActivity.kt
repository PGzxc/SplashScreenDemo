package com.pgzxc.splashscreendemo

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.VideoView
import androidx.core.app.ComponentActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView

@SuppressLint("RestrictedApi")
class SplashExpoPlayerActivity : ComponentActivity() {
    private var player: ExoPlayer? = null
    private lateinit var playerView: PlayerView

    @SuppressLint("MissingSuperCall")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 安装启动画面，快速过渡
        installSplashScreen().setKeepOnScreenCondition { false }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_expoplayer)

        playerView = findViewById(R.id.media_player)

        // 初始化 ExoPlayer
        player = ExoPlayer.Builder(this).build().also { exoPlayer ->
            playerView.player = exoPlayer

            // 读取本地 raw 资源视频
            val videoUri = Uri.parse("android.resource://${packageName}/${R.raw.splash_video}")

            val mediaItem = MediaItem.fromUri(videoUri)
            exoPlayer.setMediaItem(mediaItem)

            exoPlayer.repeatMode = Player.REPEAT_MODE_OFF
            exoPlayer.prepare()
            exoPlayer.play()
        }

        // 监听播放结束
        player?.addListener(object : Player.Listener {
            override fun onPlaybackStateChanged(state: Int) {
                if (state == Player.STATE_ENDED) {
                    goToMain()
                }
            }
        })

    }
    private fun goToMain() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun onStop() {
        super.onStop()
        player?.release()
        player = null
    }
}