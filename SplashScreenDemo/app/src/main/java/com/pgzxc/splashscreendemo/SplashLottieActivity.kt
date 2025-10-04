package com.pgzxc.splashscreendemo

import android.animation.Animator
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.core.app.ComponentActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.airbnb.lottie.LottieAnimationView

@SuppressLint("RestrictedApi")
class SplashLottieActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        // 快速结束启动画面
        splashScreen.setKeepOnScreenCondition { false }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_lottie)

        val lottieView = findViewById<LottieAnimationView>(R.id.lottie_animation)
        lottieView.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationEnd(animation: Animator) {
                startActivity(Intent(this@SplashLottieActivity, MainActivity::class.java))
                finish()
            }
            override fun onAnimationStart(animation: Animator) {}
            override fun onAnimationCancel(animation: Animator) {}
            override fun onAnimationRepeat(animation: Animator) {}
        })

        // 点击跳过
        lottieView.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    private fun lottieNormal() {
        val lottieView = findViewById<LottieAnimationView>(R.id.lottie_animation)
        lottieView.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationEnd(animation: Animator) {
                startActivity(Intent(this@SplashLottieActivity, MainActivity::class.java))
                finish()
            }

            // 其他回调方法
            override fun onAnimationStart(animation: Animator) {}
            override fun onAnimationCancel(animation: Animator) {}
            override fun onAnimationRepeat(animation: Animator) {}
        })
    }
}