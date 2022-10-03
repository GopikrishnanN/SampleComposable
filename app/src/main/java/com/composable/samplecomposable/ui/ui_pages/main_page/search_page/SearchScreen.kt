package com.composable.samplecomposable.ui.ui_pages.main_page.search_page

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.composable.samplecomposable.R

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
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp
    val screenWidth = configuration.screenWidthDp

    LazyColumn {
        item {
            Column {
                Spacer(modifier = Modifier.height(8.dp))
                SearchBar()
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
        item {
            LazyVerticalGrid(
                modifier = Modifier
                    .height((screenHeight * .85).dp)
                    .padding(horizontal = 12.dp),
                columns = GridCells.Fixed(2)
            ) {
                items(10) { count ->
                    BoxWithConstraints {
                        Box(modifier = Modifier.padding(5.dp)) {
                            Card(
                                modifier = Modifier.width((screenWidth * .45).dp),
                                backgroundColor = colorResource(id = R.color.white),
                                elevation = 6.dp,
                            ) {
                                Column {
                                    Spacer(modifier = Modifier.height(8.dp))
                                    SubcomposeAsyncImage(
                                        model = "https://www.freepnglogos.com/uploads/burger-png/burger-png-png-images-yellow-images-12.png",
                                        loading = {
//                                        if (painter.state is AsyncImagePainter.State.Loading || painter.state is AsyncImagePainter.State.Error) {
//                                            CircularProgressIndicator(color = colorResource(id = R.color.black))
//                                        } else {
                                            SubcomposeAsyncImageContent(
                                                painter = painterResource(
                                                    id = R.drawable.tuk_in_logo
                                                )
                                            )
//                                        }
                                        },
                                        contentDescription = "$count",
                                        modifier = Modifier
                                            .size(120.dp)
                                            .align(alignment = Alignment.CenterHorizontally),
                                    )
                                    Text(
                                        text = "Bacon Burger",
                                        style = TextStyle(
                                            fontWeight = FontWeight.Black,
                                            color = colorResource(id = R.color.black),
                                            fontSize = 14.sp,
                                        ),
                                        modifier = Modifier.padding(horizontal = 10.dp),
                                    )
                                    Text(
                                        text = "yahoo camida",
                                        style = TextStyle(
                                            fontWeight = FontWeight.Normal,
                                            color = colorResource(id = R.color.black).copy(.5F),
                                            fontSize = 12.sp,
                                        ),
                                        modifier = Modifier.padding(horizontal = 10.dp),
                                    )
                                    Spacer(modifier = Modifier.height(6.dp))
                                    val offersAmountText =
                                        buildAnnotatedString {
                                            withStyle(
                                                style = SpanStyle(
                                                    Color.Black, fontSize = 14.sp
                                                )
                                            ) {
                                                append("   ₹ 150")
                                            }
                                            withStyle(
                                                style = SpanStyle(
                                                    Color.Red,
                                                    fontSize = 13.sp,
                                                    fontWeight = FontWeight.SemiBold,
                                                )
                                            ) {
                                                append(" 40% off")
                                            }
                                        }
                                    Spacer(modifier = Modifier.height(5.dp))
                                    Row {
                                        Text(text = offersAmountText)
                                        Spacer(modifier = Modifier.width(5.dp))
                                        Box(
                                            modifier = Modifier.border(
                                                width = .5.dp,
                                                shape = RoundedCornerShape(2.dp),
                                                brush = Brush.horizontalGradient(
                                                    listOf(
                                                        Color.Black.copy(.5f),
                                                        Color.Black.copy(.5f)
                                                    )
                                                )
                                            )
                                        ) {
                                            Text(
                                                text = "ADD",
                                                style = TextStyle(
                                                    color = Color(0xff3b732e),
                                                    fontSize = 10.sp,
                                                    fontWeight = FontWeight.Medium,
                                                ),
                                                modifier = Modifier.padding(
                                                    horizontal = 10.dp, vertical = 2.dp
                                                ),
                                            )
                                        }
                                    }
                                    Spacer(modifier = Modifier.height(12.dp))
                                }
                            }

                        }
                        BoxWithConstraints {
                            Image(
                                painter = painterResource(id = R.drawable.ic_tag_icon),
                                contentDescription = "ic_tag_icon",
                                modifier = Modifier
                                    .width(width = 90.dp)
                                    .height(height = 25.dp),
                                contentScale = ContentScale.FillBounds,
                                alignment = Alignment.Center
                            )
                            val bestSellerText = buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(
                                        Color.White,
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.SemiBold,
                                    ),
                                ) {
                                    append("BEST")
                                }
                                withStyle(
                                    style = SpanStyle(
                                        Color.White,
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.SemiBold,
                                    )
                                ) {
                                    append(" SELLER")
                                }
                            }

                            Text(
                                text = bestSellerText, modifier = Modifier.padding(
                                    start = 8.dp, top = 5.dp
                                ), style = TextStyle(
                                    color = Color.White, fontSize = 10.sp
                                )
                            )
                        }
                    }
                }
            }
        }
    }
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
        placeholderText = "What are you looking for?"
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