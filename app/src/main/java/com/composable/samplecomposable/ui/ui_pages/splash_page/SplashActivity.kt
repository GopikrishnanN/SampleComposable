package com.composable.samplecomposable.ui.ui_pages.splash_page

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.composable.samplecomposable.R
import com.composable.samplecomposable.ui.base.BaseUIActivity
import com.composable.samplecomposable.ui.theme.SampleComposableTheme
import com.composable.samplecomposable.ui.ui_pages.walkthrough.WalkthroughActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseUIActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SampleComposableTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {
                    ScaffoldView()
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ScaffoldView() {
    Scaffold(
//        topBar = { TopBar(title = "", isActions = false, elevation = 0.dp) },
        backgroundColor = Color.White,
        content = { SplashBody() },
    )
}

@Composable
fun SplashBody() {
    val activity = (LocalContext.current as? Activity)
    val mContext = LocalContext.current
    val img = painterResource(id = R.drawable.tuk_in_logo)
    val modifier = Modifier.fillMaxSize()

    Handler(Looper.myLooper()!!).postDelayed({
        activity?.finish()
        mContext.startActivity(Intent(mContext, WalkthroughActivity::class.java))
    }, 1000)

    Image(
        img,
        contentDescription = "",
        alignment = Alignment.Center,
        contentScale = ContentScale.Fit,
        modifier = modifier
    )
}
