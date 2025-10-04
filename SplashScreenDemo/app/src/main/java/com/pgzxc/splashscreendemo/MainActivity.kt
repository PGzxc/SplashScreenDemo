package com.pgzxc.splashscreendemo

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.pgzxc.splashscreendemo.ui.theme.SplashScreenDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashInit()
        enableEdgeToEdge()
        setContent {
            SplashScreenDemoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

    private fun splashInit() {
        val splashScreen = installSplashScreen()
        // 保持 2 秒
        splashScreen.setKeepOnScreenCondition {
            SystemClock.elapsedRealtime() < 2000
        }

        splashScreen.setOnExitAnimationListener { splashScreenViewProvider ->
            // 去掉图标（通过替换成透明图标）
            splashScreenViewProvider.iconView.visibility = View.GONE
            splashScreenViewProvider.remove()

            // 淡出动画
//            splashScreenViewProvider.view.animate()
//                .alpha(0f)
//                .setDuration(500)
//                .setListener(object : AnimatorListenerAdapter() {
//                    override fun onAnimationEnd(animation: Animator) {
//                        splashScreenViewProvider.remove()
//                    }
//                })
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SplashScreenDemoTheme {
        Greeting("Android")
    }
}