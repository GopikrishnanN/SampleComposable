package com.composable.samplecomposable.ui.ui_pages.walkthrough

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.composable.samplecomposable.R
import com.composable.samplecomposable.ui.base.BaseUIActivity
import com.composable.samplecomposable.ui.theme.SampleComposableTheme
import com.composable.samplecomposable.ui.ui_pages.login.LoginActivity
import com.composable.samplecomposable.ui.ui_pages.main_page.FoodsActivity
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch

class WalkthroughActivity : BaseUIActivity() {
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

@Preview
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ScaffoldView() {
    Scaffold(
        backgroundColor = Color.White,
        content = { WalkthroughBody() },
    )
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun WalkthroughBody() {
    val activity = (LocalContext.current as? Activity)
    val mContext = LocalContext.current
    val img = painterResource(id = R.drawable.tuk_in_logo)
    val modifier = Modifier
        .height(height = 250.dp)
        .width(width = 250.dp)

    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp
//    val screenWidth = configuration.screenWidthDp

    val state = rememberPagerState()
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            count = 2,
            state = state
        ) { page ->
            Column(
                modifier = Modifier.height((screenHeight * .8).dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(50.dp))
                Image(
                    img,
                    contentDescription = "tuk_in_logo",
                    alignment = Alignment.Center,
                    modifier = modifier
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = if (page == 0) "Fresh Foods" else "Easy Payment",
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "In particular, the garbled words of bear an unmistakable",
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
        HorizontalPagerIndicator(
            pagerState = state,
            activeColor = Color.Red,
            inactiveColor = Color.Gray,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = {
                if (state.currentPage < 1) {
                    scope.launch {
                        state.scrollToPage(state.currentPage + 1)
                        // or
                        state.scrollBy(100f)
                    }
                } else {
                    activity?.finish()
                    mContext.startActivity(Intent(mContext, FoodsActivity::class.java))
                }
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red),
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .width(200.dp)
                .height(40.dp)
                .align(Alignment.CenterHorizontally),
        ) {
            Text(
                text = if (state.currentPage == 0) "Next" else "Submit",
                modifier = Modifier.fillMaxWidth(),
                color = Color.White,
                textAlign = TextAlign.Center,
            )
        }
        TextButton(onClick = {
            activity?.finish()
//            mContext.startActivity(Intent(mContext, FoodsActivity::class.java))
            mContext.startActivity(Intent(mContext, LoginActivity::class.java))
        }) {
            Text("Skip", style = TextStyle(color = Color.Black))
        }
    }
}