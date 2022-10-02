package com.composable.samplecomposable.ui.ui_pages.main_page.search_page

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SearchScreen(text: String) = Surface(modifier = Modifier.fillMaxSize()) {
    Scaffold(backgroundColor = Color.White, content = {
        Text(
            text = text,
            style = TextStyle(color = Color.Black)
        )
    })
}