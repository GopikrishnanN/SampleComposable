package com.composable.samplecomposable.ui.ui_pages.main_page.home_page

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.rememberNestedScrollInteropConnection
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.composable.samplecomposable.R
import com.composable.samplecomposable.ui.base.BaseUIActivity
import com.composable.samplecomposable.ui.theme.SampleComposableTheme
import com.google.accompanist.flowlayout.FlowColumn
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.SizeMode
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeScreen : BaseUIActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SampleComposableTheme {
                SurfaceView()
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SurfaceView() = Surface(modifier = Modifier.fillMaxSize()) {
    Scaffold(
        topBar = { HomeTopBar() },
        backgroundColor = Color.White,
        content = { HomeBody() },
    )
}

@Composable
fun HomeTopBar() {

}

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalPagerApi::class, ExperimentalComposeUiApi::class)
@Composable
fun HomeBody() {

    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp
    val screenWidth = configuration.screenWidthDp

    val b1Image = painterResource(id = R.drawable.banner_one)
    val b2Image = painterResource(id = R.drawable.banner_two)
    val b3Image = painterResource(id = R.drawable.banner_three)
    val b4Image = painterResource(id = R.drawable.banner_four)
    val b5Image = painterResource(id = R.drawable.banner_five)

    val items = listOf(b1Image, b2Image, b3Image, b4Image, b5Image)

    val cardHeight = (screenHeight * .2).dp
    val cardWidth = (screenWidth * .95).dp

    val modifierBanner = Modifier
        .width(width = cardWidth)
        .height(height = cardHeight)

    val state = rememberPagerState()
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        delay(5000)
        while (true) {
            scope.launch { /* right */
                if (state.currentPage == items.lastIndex) {
                    state.animateScrollToPage(0)
                } else {
                    state.animateScrollToPage(state.currentPage + 1)
                }
            }
            delay(5000)
        }
    }

    val modifierPager = Modifier.pointerInput(Unit) {
        detectHorizontalDragGestures { change, dragAmount ->
            change.consume()
            when {
                dragAmount < 0 -> {
                    scope.launch { /* right */
                        if (state.currentPage == items.lastIndex) {
                            state.animateScrollToPage(0)
                        } else {
                            state.animateScrollToPage(state.currentPage + 1)
                        }
                    }
                }
                dragAmount > 0 -> { /* left */
                    scope.launch {
                        if (state.currentPage == 0) {
                            state.animateScrollToPage(items.lastIndex)
                        } else {
                            state.animateScrollToPage(state.currentPage - 1)
                        }
                    }
                }
            }
        }
    }

    lateinit var courseList: List<ListModal>
    courseList = ArrayList()

    // on below line we are adding data to our list.
    courseList = courseList + ListModal("Burger", R.drawable.ic_food_backside_icon)
    courseList = courseList + ListModal("Breakfast", R.drawable.ic_food_backside_icon)
    courseList = courseList + ListModal("Sweets", R.drawable.ic_food_backside_icon)
    courseList = courseList + ListModal("Fruits", R.drawable.ic_food_backside_icon)
    courseList = courseList + ListModal("Rice", R.drawable.ic_food_backside_icon)
    courseList = courseList + ListModal("Tiffin", R.drawable.ic_food_backside_icon)
    courseList = courseList + ListModal("Juice", R.drawable.ic_food_backside_icon)

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.Start,
    ) {
        item {
            Spacer(modifier = Modifier.height(8.dp))
            HorizontalPager(
                count = 5,
                state = state,
                contentPadding = PaddingValues(start = 8.dp, end = 24.dp),
                modifier = modifierPager
            ) { page ->
                Row {
                    Card(
                        backgroundColor = Color.White,
                        modifier = modifierBanner.padding(horizontal = 8.dp),
                        shape = RoundedCornerShape(12.dp),
                    ) {
                        Image(
                            painter = items[page],
                            contentDescription = "items[$page]",
                            modifier = modifierBanner,
                            contentScale = ContentScale.FillBounds,
                            alignment = Alignment.Center
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            SearchBar()
            Spacer(modifier = Modifier.height(12.dp))
            Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                Text(
                    text = "All Products", textAlign = TextAlign.Start, style = TextStyle(
                        fontWeight = FontWeight.SemiBold, color = colorResource(id = R.color.black)
                    )
                )
                Spacer(modifier = Modifier.height(12.dp))
                LazyRow {
                    items(courseList.size) { count ->
                        Column(
                            modifier = Modifier.align(Alignment.CenterHorizontally),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(id = courseList[count].languageImg),
                                contentDescription = "items[$count]",
                                modifier = Modifier
                                    .width(width = 70.dp)
                                    .height(height = 70.dp),
                                contentScale = ContentScale.FillBounds,
                                alignment = Alignment.Center
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(
                                text = courseList[count].languageName,
                                style = TextStyle(color = Color.Black),
                                textAlign = TextAlign.Center,
                            )
                        }
                        Spacer(modifier = Modifier.width(5.dp))
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
        item {
            Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                Text(
                    text = "Most popular", textAlign = TextAlign.Start, style = TextStyle(
                        fontWeight = FontWeight.SemiBold, color = colorResource(id = R.color.black)
                    )
                )
                Spacer(modifier = Modifier.height(12.dp))
                LazyRow {
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
                                            androidx.compose.ui.text.buildAnnotatedString {
                                                withStyle(
                                                    style = androidx.compose.ui.text.SpanStyle(
                                                        Color.Black, fontSize = 14.sp
                                                    )
                                                ) {
                                                    append("   ₹ 150")
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
                                        Spacer(modifier = Modifier.height(5.dp))
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
                                val bestSellerText = androidx.compose.ui.text.buildAnnotatedString {
                                    withStyle(
                                        style = androidx.compose.ui.text.SpanStyle(
                                            Color.White,
                                            fontSize = 10.sp,
                                            fontWeight = FontWeight.SemiBold,
                                        ),
                                    ) {
                                        append("BEST")
                                    }
                                    withStyle(
                                        style = androidx.compose.ui.text.SpanStyle(
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
        item {
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun SearchBar() {
    var textState by rememberSaveable { mutableStateOf("") }
    CustomTextField(
        text = textState,
        onValueChange = { textState = it },
        leadingIcon = {
            Icon(
                Icons.Filled.Search,
                null,
                tint = Color.Gray,
//                tint = LocalContentColor.current.copy(alpha = 0.3f)
            )
        },
        trailingIcon = {
            if (textState.isNotEmpty()) {
                Icon(
                    Icons.Filled.Close,
                    null,
                    tint = Color.Gray,
                )
            }
        },
        modifierMargin = Modifier.padding(horizontal = 20.dp),
        modifier = Modifier
            .background(
//                MaterialTheme.colors.surface,
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
//                MaterialTheme.colors.surface,
                Color.Gray.copy(.0f),
                MaterialTheme.shapes.small,
            )
            .fillMaxWidth(),
            value = text,
            onValueChange = onValueChange,
            singleLine = true,
            cursorBrush = SolidColor(Color.Black/*MaterialTheme.colors.primary*/),
            textStyle = LocalTextStyle.current.copy(
//                color = MaterialTheme.colors.onSurface,
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
//                                color = MaterialTheme.colors.onSurface.copy(alpha = 0.3f),
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
