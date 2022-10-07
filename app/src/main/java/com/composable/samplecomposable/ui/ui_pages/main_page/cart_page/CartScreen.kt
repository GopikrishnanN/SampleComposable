package com.composable.samplecomposable.ui.ui_pages.main_page.cart_page

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.composable.samplecomposable.R

@Preview
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CartScreen() = Surface(modifier = Modifier.fillMaxSize()) {
    Scaffold(backgroundColor = Color.White, content = {
        Scaffold(
            backgroundColor = Color.White,
            content = { CartBody() },
        )
    })
}


@Composable
fun CartBody() {
//    val activity = (LocalContext.current as? Activity)
//    val mContext = LocalContext.current
    LazyColumn {
        items(count = 5) {
            CartItems()
        }
        item {
            Spacer(modifier = Modifier.height(8.dp))
            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                SearchBar()
                Button(
                    onClick = {
//                        activity?.finish()
//                        mContext.startActivity(Intent(mContext, FoodsActivity::class.java))
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red),
                    shape = RoundedCornerShape(5.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                        .padding(end = 20.dp),
                ) {
                    Text(
                        text = "Apply coupon",
                        modifier = Modifier.fillMaxWidth(),
                        color = Color.White,
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }
        item {
            SummeryView()
        }
    }
}

@Composable
fun SummeryView() {

    val grayTextStyle = TextStyle(color = Color.Gray, fontSize = 14.sp)
    val boldTextStyle =
        TextStyle(color = Color.Black, fontSize = 16.sp, fontWeight = FontWeight.Medium)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        Spacer(modifier = Modifier.height(12.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(text = "Order total", style = grayTextStyle)
            Text(text = "₹ 450.00", style = grayTextStyle)
        }
        Spacer(modifier = Modifier.height(12.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(text = "Delivery Charge", style = grayTextStyle)
            Text(text = "Free", style = grayTextStyle)
        }
        Spacer(modifier = Modifier.height(12.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(text = "Total price", style = boldTextStyle)
            Text(text = "₹ 450.00", style = boldTextStyle)
        }
        Spacer(modifier = Modifier.height(35.dp))
        Button(
            onClick = {

            },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red),
            shape = RoundedCornerShape(5.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp),
        ) {
            Text(
                text = "Pay Now",
                modifier = Modifier.fillMaxWidth(),
                color = Color.White,
                textAlign = TextAlign.Center,
            )
        }
        Spacer(modifier = Modifier.height(60.dp))
    }
}

@Composable
fun CartItems() {
    Box(modifier = Modifier.padding(vertical = 10.dp)) {
        Card(
            elevation = 8.dp, modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            backgroundColor = Color.White
        ) {
            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Bacon Burger", style = TextStyle(color = Color.Black))
                    Text("yahoo comida", style = TextStyle(color = Color.Black))
                    Spacer(modifier = Modifier.height(8.dp))
                    val offersAmountText =
                        buildAnnotatedString {
                            withStyle(
                                style = androidx.compose.ui.text.SpanStyle(
                                    Color.Black, fontSize = 14.sp
                                )
                            ) {
                                append("₹ 150")
                            }
                            withStyle(
                                style = androidx.compose.ui.text.SpanStyle(
                                    Color.Red,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.SemiBold,
                                )
                            ) {
                                append(" 40% off")
                            }
                        }
                    Row {
                        Text(text = offersAmountText)
                        Spacer(modifier = Modifier.width(5.dp))
                        Box(
                            modifier = Modifier.border(
                                width = .5.dp,
                                shape = RoundedCornerShape(2.dp),
                                brush = androidx.compose.ui.graphics.Brush.horizontalGradient(
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
                SubcomposeAsyncImage(
                    model = "https://www.freepnglogos.com/uploads/burger-png/burger-png-png-images-yellow-images-12.png",
                    loading = {
                        SubcomposeAsyncImageContent(
                            painter = painterResource(
                                id = R.drawable.tuk_in_logo
                            )
                        )
                    },
                    contentDescription = "count",
                    modifier = Modifier
                        .size(80.dp)
                        .padding(end = 20.dp),
                )
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
        },
        modifierMargin = Modifier.padding(start = 20.dp, end = 5.dp),
        modifier = Modifier
            .background(
                Color.Gray.copy(.25f), RoundedCornerShape(percent = 10)
            )
            .padding(horizontal = 4.dp)
            .height(40.dp),
        fontSize = 12.sp,
        placeholderText = "Enter voucher code"
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
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp
    val screenWidth = configuration.screenWidthDp
    Box(modifier = modifierMargin) {
        BasicTextField(modifier = modifier
            .background(
                Color.Gray.copy(.0f),
                MaterialTheme.shapes.small,
            )
            .width((screenWidth * .5).dp),
            value = text,
            onValueChange = onValueChange,
            singleLine = true,
            cursorBrush = SolidColor(Color.Black),
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
