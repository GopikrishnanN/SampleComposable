package com.composable.samplecomposable.ui.ui_pages.login

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.composable.samplecomposable.R
import com.composable.samplecomposable.ui.base.BaseUIActivity
import com.composable.samplecomposable.ui.base.TopBar
import com.composable.samplecomposable.ui.theme.SampleComposableTheme
import com.composable.samplecomposable.ui.ui_pages.main_page.FoodsActivity

class LoginActivity : BaseUIActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SampleComposableTheme {
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
    Scaffold(topBar = { TopBar(title = "", isActions = false, elevation = 0.dp) },
        backgroundColor = Color.White,
        content = { LoginBody() })
}

@Composable
fun LoginBody() {
    val activity = (LocalContext.current as? Activity)
    val mContext = LocalContext.current
    val img = painterResource(id = R.drawable.tuk_in_logo)
    val fbImage = painterResource(id = R.drawable.facebook_circle_icon)
    val twitterImage = painterResource(id = R.drawable.twitter_circle_icon)
    val gpPlusImage = painterResource(id = R.drawable.google_plus_circle_icon)
    val modifier = Modifier
        .height(height = 250.dp)
        .width(width = 250.dp)
    val modifierSocial = Modifier
        .height(height = 35.dp)
        .width(width = 35.dp)
    var textState by rememberSaveable { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            img,
            contentDescription = "tuk_in_logo",
            alignment = Alignment.Center,
            modifier = modifier
        )
        Spacer(modifier = Modifier.height(20.dp))
        TextField(modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.5.dp,
                shape = RoundedCornerShape(5.dp),
                brush = Brush.horizontalGradient(
                    listOf(Black.copy(.5f), Black.copy(.5f))
                )
            ), value = textState, colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
            cursorColor = Black,
            disabledLabelColor = Blue.copy(.5f),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ), placeholder = { Text(text = "UserName") }, onValueChange = {
            textState = it
        }, textStyle = TextStyle(color = Black), singleLine = true, leadingIcon = {
            IconButton(onClick = { textState = "" }) {
                Icon(
                    imageVector = Icons.Outlined.Person,
                    contentDescription = null,
                    tint = Color.Red,
                )
            }
        }, trailingIcon = {
            if (textState.isNotEmpty()) {
                IconButton(onClick = { textState = "" }) {
                    Icon(
                        imageVector = Icons.Outlined.Close,
                        contentDescription = null,
                        tint = Color.Red,
                    )
                }
            }
        })
        Spacer(modifier = Modifier.height(10.dp))
        TextField(modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.5.dp,
                shape = RoundedCornerShape(5.dp),
                brush = Brush.horizontalGradient(
                    listOf(Black.copy(.5f), Black.copy(.5f))
                )
            ), value = textState, colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
            cursorColor = Black,
            disabledLabelColor = Blue.copy(.5f),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ), placeholder = { Text(text = "Email") }, onValueChange = {
            textState = it
        }, textStyle = TextStyle(color = Black), singleLine = true, leadingIcon = {
            IconButton(onClick = { textState = "" }) {
                Icon(
                    imageVector = Icons.Outlined.Email,
                    contentDescription = null,
                    tint = Color.Red,
                )
            }
        }, trailingIcon = {
            if (textState.isNotEmpty()) {
                IconButton(onClick = { textState = "" }) {
                    Icon(
                        imageVector = Icons.Outlined.Close,
                        contentDescription = null,
                        tint = Color.Red,
                    )
                }
            }
        })
        Spacer(modifier = Modifier.height(30.dp))
        Button(
            onClick = {
                activity?.finish()
                mContext.startActivity(Intent(mContext, FoodsActivity::class.java))
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red),
            shape = RoundedCornerShape(5.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .align(Alignment.CenterHorizontally),
        ) {
            Text(
                text = "Log In",
                modifier = Modifier.fillMaxWidth(),
                color = Color.White,
                textAlign = TextAlign.Center,
            )
        }
        val annotatedString = buildAnnotatedString {
            withStyle(style = SpanStyle(Gray, fontSize = 12.sp)) {
                append("Not registered yet? Please, ")
            }
            withStyle(
                style = SpanStyle(
                    Black,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
            ) {
                append("Sign Up")
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        ClickableText(
            text = annotatedString,
            style = MaterialTheme.typography.body1,
            onClick = { offset ->
                annotatedString.getStringAnnotations(tag = "Sign Up", start = offset, end = offset)
                    .firstOrNull()?.let {
                        Log.d("Sign Up", it.item)
                    }
            })
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Divider(
                color = Gray.copy(0.5f),
                modifier = Modifier
                    .width(150.dp)
                    .height(0.5.dp)
            )
            Text(text = "OR", style = TextStyle(color = Black, fontWeight = FontWeight.SemiBold))
            Divider(
                color = Gray.copy(0.5f),
                modifier = Modifier
                    .width(150.dp)
                    .height(0.5.dp)
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                fbImage,
                contentDescription = "facebook_circle_icon",
                alignment = Alignment.Center,
                modifier = modifierSocial
            )
            Spacer(modifier = Modifier.width(10.dp))
            Image(
                twitterImage,
                contentDescription = "twitter_circle_icon",
                alignment = Alignment.Center,
                modifier = modifierSocial
            )
            Spacer(modifier = Modifier.width(10.dp))
            Image(
                gpPlusImage,
                contentDescription = "google_plus_circle_icon",
                alignment = Alignment.Center,
                modifier = modifierSocial
            )
        }
    }
}
