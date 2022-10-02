package com.composable.samplecomposable

import android.annotation.SuppressLint

import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.composable.samplecomposable.ui.base.BaseUIActivity
import com.composable.samplecomposable.ui.base.TopBar
import com.composable.samplecomposable.ui.theme.SampleComposableTheme
import mSettings

class MainActivity : BaseUIActivity() {
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
    val mContext = LocalContext.current
    Scaffold(
        topBar = {
            TopBar(title = "Main Page", isActions = true, onSuffixClick = {
                if (it == mSettings) {
                    Toast.makeText(mContext, mSettings, Toast.LENGTH_SHORT).show()
                }
            })
        },
        backgroundColor = Color(0xFFE28743),
        content = { /*Drawer()*/ Chess() },
//        bottomBar = { BottomBar() },
    ) /*{
        JetpackComposable()
    }*/
}

//@Composable
//fun TopBar() {
//    TopAppBar(title = {
//        Text(text = "Sample Composable")
//    }, navigationIcon = {
//        IconButton(onClick = {}) {
//            Icon(Icons.Filled.Settings, "backIcon")
//        }
//    }, backgroundColor = Color.White, contentColor = Color.Black, elevation = 10.dp
//    )
//}

//@Composable
//fun Drawer() {
//    // Column Composable
//    Column(
//        Modifier
//            .background(Color.White)
//            .fillMaxSize()
//    ) {
//        val mContext = LocalContext.current
//        // Repeat is a loop which
//        // takes count as argument
//        repeat(5) { item ->
//            Box(
//                modifier = Modifier
//                    .clickable(onClick = {
//                        Toast
//                            .makeText(mContext, "clickable", Toast.LENGTH_LONG)
//                            .show()
//                    })
//                    .background(
//                        brush = Brush.verticalGradient(
//                            colors = listOf(
//                                Color.Black.copy(alpha = 0.1f),
//                                Color.Transparent,
//                                Color.Black.copy(alpha = 0.1f),
//                            ), startY = 50f
//                        )
//                    )
//                    .fillMaxWidth()
//            ) {
//                Card(
//                    backgroundColor = Color.Black,
//                    elevation = 10.dp,
//                    modifier = Modifier
//                        .padding(horizontal = 15.dp, vertical = 5.dp)
//                        .height(100.dp)
//                        .fillMaxWidth()
//                ) {
//                    Text(
//                        text = "Item number ${item + 1}",
//                        modifier = Modifier
//                            .align(Alignment.Center)
//                            .padding(8.dp),
//                        color = Color.White
//                    )
//                }
//            }
//        }
//    }
//}

@Composable
fun Chess() {
    val mContext = LocalContext.current
    val darkSquare = Color(0xFF000000)
    val lightSquare = Color(0xFFFFFFFF)
    var expanded by remember {
        mutableStateOf(false)
    }
    Column {
        for (i in 0 until 8) {
            AnimatedVisibility(expanded) {
                Row {
                    for (j in 0 until 8) {
                        val isLightSquare = i % 2 == j % 2
                        val squareColor = if (isLightSquare) lightSquare else darkSquare
                        Box(modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1f)
                            .background(squareColor)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = rememberRipple(
                                    bounded = true, color = Color(0xFFABDBE3), radius = 20.dp
                                ),
                            ) {
                                Toast
                                    .makeText(mContext, "${i + j}", Toast.LENGTH_SHORT)
                                    .show()
                            }) {
                            Text(
                                text = "${i + j}",
                                modifier = Modifier.align(Alignment.Center),
                                color = if (!isLightSquare) lightSquare else darkSquare
                            )
                        }

                    }
                }
            }
        }
        Button(
            border = BorderStroke(width = 1.dp, brush = SolidColor(Color.Black)),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
            onClick = {
                expanded = !expanded
            },
            modifier = Modifier.align(Alignment.CenterHorizontally),
        ) {
            Text(text = "Open Chess", color = Color.Black)
        }
//        VideoPlayer()
//        JetpackComposable(expanded)
    }
}

//@Composable
//fun Greeting(name: String) {
////    Text(text = "Hello $name!")
//    LazyColumn {
//        // Add a single item
//        item {
////            Text(text = "First item")
//            Card(backgroundColor = Color.White) {
////                Box{
////                    Modifier.size(width = 150, height = 50) {
//                Text(
//                    text = "First item", color = Color.Black
//                )
////                    }
////                }
//            }
//        }
//
//        // Add 10 items
//        items(10) { index ->
//            Text(text = "Item: ${index + 1}")
//        }
//
//        // Add another single item
//        item {
//            Text(text = "Last item")
//        }
//    }
//}

//@Composable
//fun JetpackComposable(isExpanded: Boolean) {
//    var isExpandedValue = isExpanded
//    Card(modifier = Modifier.fillMaxSize(), backgroundColor = Color.White) {
//        Column(Modifier.clickable { isExpandedValue = !isExpandedValue }) {
//            AnimatedVisibility(isExpandedValue) {
//                Text(
//                    text = "Animated Visibility",
//                    style = MaterialTheme.typography.h6,
//                    color = MaterialTheme.colors.background
//                )
//            }
//        }
//    }
//}
//
//@Composable
//fun BottomBar() {
//    val selectedIndex = remember { mutableStateOf(0) }
//    BottomNavigation(elevation = 10.dp) {
//
//        BottomNavigationItem(icon = {
//            Icon(imageVector = Icons.Default.Home, "")
//        }, label = { Text(text = "Home") }, selected = (selectedIndex.value == 0), onClick = {
//            selectedIndex.value = 0
//        })
//
//        BottomNavigationItem(icon = {
//            Icon(imageVector = Icons.Default.Favorite, "")
//        },
//            label = { Text(text = "Favorite") },
//            selected = (selectedIndex.value == 1),
//            onClick = {
//                selectedIndex.value = 1
//            })
//
//        BottomNavigationItem(icon = {
//            Icon(imageVector = Icons.Default.Person, "")
//        },
//            label = { Text(text = "Profile") },
//            selected = (selectedIndex.value == 2),
//            onClick = {
//                selectedIndex.value = 2
//            })
//    }
//}

//@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
//@Composable
//fun VideoPlayer() {
//    val playWhenReady by remember { mutableStateOf(true) }
//    val context = LocalContext.current
//    val mediaItem = MediaItem.fromUri("http://techslides.com/demos/sample-videos/small.mp4")
//    val playerView = PlayerControlView(context)
//    val player = provideExoPlayer(context = context, mediaItem = mediaItem)
//    playerView.player = player
//    LaunchedEffect(player) {
//        player.prepare()
//        player.playWhenReady = playWhenReady
//    }
//    Scaffold(
//        modifier = Modifier.fillMaxSize()
//    ) {
//        AndroidView(
//            modifier = Modifier.fillMaxSize(),
//            factory = {
//                playerView
//            }
//        )
//    }
//}

//fun provideExoPlayer(context : Context, mediaItem: MediaItem): ExoPlayer {
//    val player = ExoPlayer.Builder(context).build()
//    player.setMediaItem(mediaItem)
//    return player
//}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SampleComposableTheme {
        ScaffoldView()
//        JetpackComposable()
    }
}