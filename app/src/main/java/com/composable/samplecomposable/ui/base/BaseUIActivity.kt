package com.composable.samplecomposable.ui.base

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import com.composable.samplecomposable.ui.ui_pages.splash_page.SplashActivity

open class BaseUIActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {}
    }
}

@Composable
fun TopBar(
    title: String,
    onPrefixClick: @Composable () -> Unit = {},
    onSuffixClick: @Composable (value: String) -> Unit = {},
    isActions: Boolean,
    elevation: Dp = AppBarDefaults.TopAppBarElevation,
) {
    // Fetching the Local Context
    val mActivity = (LocalContext.current as? Activity)
    val mContext = LocalContext.current

    TopAppBar(
        title = {
            Text(
                text = title, style = TextStyle(
                    color = Color.Black, fontSize = 16.sp, fontWeight = FontWeight.Medium
                )
            )
        },
        navigationIcon = {
            IconButton(onClick = {
                mActivity?.finish()
            }) {
                Icon(Icons.Filled.ArrowBack, "BackIcon")
            }
        },
        backgroundColor = Color.White, contentColor = Color.Black, elevation = elevation,
        actions = {
            if (isActions) {
                IconButton(onClick = {
                    mContext.startActivity(Intent(mContext, SplashActivity::class.java))
                }) {
                    Icon(Icons.Filled.Settings, "SettingsIcon")
                }
                IconButton(onClick = {}) {
                    Icon(Icons.Filled.Favorite, "FavoriteIcon")
                }
                IconButton(onClick = {}) {
                    Icon(Icons.Filled.Done, "DoneIcon")
                }
            }
        },
    )
}