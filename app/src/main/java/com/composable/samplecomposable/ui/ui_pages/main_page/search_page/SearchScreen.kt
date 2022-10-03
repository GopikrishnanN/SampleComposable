package com.composable.samplecomposable.ui.ui_pages.main_page.search_page

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SearchSurfaceView() = Surface(modifier = Modifier.fillMaxSize()) {
    Scaffold(
        backgroundColor = Color.White,
        content = { SearchBody() },
    )
}

@Composable
fun SearchBody() {
    SearchBar()
}

@Composable
fun SearchBar() {
    var textState by rememberSaveable { mutableStateOf("") }
    CustomTextField(
        text = textState,
        onValueChange = { textState = it },
        leadingIcon = null,
        trailingIcon = {
            if (textState.isNotEmpty()) {
                Icon(
                    Icons.Filled.Close,
                    null,
                    tint = Color.Gray,
                )
            }
            Icon(
                Icons.Filled.Search,
                null,
                tint = Color.Gray,
            )
        },
        modifierMargin = Modifier.padding(horizontal = 20.dp),
        modifier = Modifier
            .background(
                Color.Gray.copy(.25f), RoundedCornerShape(percent = 10)
            )
            .padding(horizontal = 4.dp)
            .height(40.dp),
        fontSize = 12.sp,
        placeholderText = "Search product..."
    )
}


@Composable
private fun CustomTextField(
    modifier: Modifier = Modifier,
    modifierMargin: Modifier = Modifier,
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
    placeholderText: String = "Placeholder",
    text: String = "",
    onValueChange: (String) -> Unit,
    fontSize: TextUnit = MaterialTheme.typography.body2.fontSize
) {
    Box(modifier = modifierMargin) {
        BasicTextField(modifier = modifier
            .background(
                Color.Gray.copy(.0f),
                MaterialTheme.shapes.small,
            )
            .fillMaxWidth(),
            value = text,
            onValueChange = onValueChange,
            singleLine = true,
            cursorBrush = SolidColor(Color.Black/*MaterialTheme.colors.primary*/),
            textStyle = LocalTextStyle.current.copy(
                color = Color.Black, fontSize = fontSize
            ),
            decorationBox = { innerTextField ->
                Row(
                    Modifier
                        .background(
                            Color.Transparent, RoundedCornerShape(percent = 10)
                        )
                        .height(40.dp), verticalAlignment = Alignment.CenterVertically
                ) {
                    if (leadingIcon != null) leadingIcon()
                    Spacer(modifier = Modifier.width(5.dp))
                    Box(Modifier.weight(1f)) {
                        if (text.isEmpty()) Text(
                            placeholderText, style = LocalTextStyle.current.copy(
                                color = Color.Gray, fontSize = fontSize
                            )
                        )
                        innerTextField()
                    }
                    Spacer(modifier = Modifier.width(5.dp))
                    if (trailingIcon != null) trailingIcon()
                }
            })
    }
}