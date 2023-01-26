package com.example.composetest

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.composetest.ui.theme.ComposeTestTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
//                    Greeting("Android")
//                    Container()
//                    VerticalContainer()
                    ButtonContainer()
                }
            }
        }
    }
}

@Composable
fun Container(){
    Row(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        DummyBox()
        DummyBox()
        DummyBox()
    }
}

@Composable
fun VerticalContainer(){
    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DummyBox()
        DummyBox()
        DummyBox()
    }
}

@Composable
fun BoxContainer(){
    Box(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        DummyBox(modifier = Modifier.size(200.dp), color = Color.DarkGray)
        DummyBox(modifier = Modifier.size(150.dp), color = Color.Magenta)
        DummyBox(color = Color.Cyan)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ButtonContainer(){

    val buttonBorderGradient = Brush.horizontalGradient(listOf(Color.Blue, Color.Red, Color.Yellow))

    val interactionSource = remember { MutableInteractionSource() }

    val isPressed by interactionSource.collectIsPressedAsState()

    val pressedState = if (isPressed) "버튼 click" else "버튼 no click"

    val interactionSourceSecond = remember { MutableInteractionSource() }

    val isPressedSecond by interactionSourceSecond.collectIsPressedAsState()

    val pressedStateSecond = if (isPressedSecond) 0.dp else 30.dp

    val pressedWithAnim : Dp by animateDpAsState(
        targetValue = if (isPressedSecond) 0.dp else 30.dp
    )
    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
       Button(
           enabled = true,
           elevation = ButtonDefaults.elevation(
               defaultElevation = 0.dp
           ),
           onClick = {
        
       }) {
            Text(text = "버튼 1")
       }
        Button(
            elevation = ButtonDefaults.elevation(
                defaultElevation = 20.dp,
                pressedElevation = 0.dp,
                disabledElevation = 0.dp
            ),
            enabled = true,
            onClick = {
                Log.d("TAG","ButtonContainer : 버튼 2 Click")
            }) {
            Text(text = "버튼 2")
        }
        Button(
            elevation = ButtonDefaults.elevation(
                defaultElevation = 20.dp,
                pressedElevation = 0.dp,
                disabledElevation = 0.dp
            ),
            enabled = true,
            shape = CircleShape,
            onClick = {
                Log.d("TAG","ButtonContainer : 버튼 3 Click")
            }) {
            Text(text = "버튼 3")
        }
        Button(
            elevation = ButtonDefaults.elevation(
                defaultElevation = 20.dp,
                pressedElevation = 0.dp,
                disabledElevation = 0.dp
            ),
            enabled = true,
            shape = RoundedCornerShape(20.dp),
            border = BorderStroke(5.dp, Color.Magenta),
            contentPadding = PaddingValues(top = 50.dp, bottom = 20.dp, start = 60.dp, end = 40.dp),
            onClick = {
                Log.d("TAG","ButtonContainer : 버튼 4 Click")
            }) {
            Text(text = "버튼 4")
        }
        Button(
            elevation = ButtonDefaults.elevation(
                defaultElevation = 20.dp,
                pressedElevation = 0.dp,
                disabledElevation = 0.dp
            ),
            enabled = true,
            shape = RoundedCornerShape(20.dp),
            border = BorderStroke(5.dp,buttonBorderGradient),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Black,
                disabledBackgroundColor = Color.Gray
            ),
            interactionSource = interactionSource,
            onClick = {
                Log.d("TAG","ButtonContainer : 버튼 5 Click")
            }) {
            Text(text = "버튼 5", color = Color.White)
        }
//        if (isPressed) Text(text = "버튼 click") else Text(text = "버튼 no click")
        Text(text = " $pressedState ")

        Button(
            elevation = ButtonDefaults.elevation(
                defaultElevation = 20.dp,
                pressedElevation = 0.dp,
                disabledElevation = 0.dp
            ),
            enabled = true,
            shape = RoundedCornerShape(20.dp),
            border = BorderStroke(5.dp,buttonBorderGradient),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Black,
                disabledBackgroundColor = Color.Gray
            ),
            interactionSource = interactionSourceSecond,
            modifier = Modifier.drawColoredShadow(
                color = Color.Cyan,
                alpha = 0.5f,
                borderRadius = 10.dp,
                shadowRadius = pressedWithAnim,
                offsetX = 0.dp,
                offsetY = 0.dp
            ),
            onClick = {
                Log.d("TAG","ButtonContainer : 버튼 6 Click")
            }) {
            Text(text = "버튼 6", color = Color.White)
        }
    }
}


@Composable
fun DummyBox(modifier: Modifier = Modifier, color: Color? = null){
    val red = Random.nextInt(256)
    val green = Random.nextInt(256)
    val blue = Random.nextInt(256)
//    val randomColor = Color(red, green, blue)
    val randomColor = color.let { it } ?: Color(red, green, blue)

    Box(modifier = modifier
        .size(100.dp)
        .background(randomColor))
}

//@Composable
//fun Greeting(name: String) {
//    Text(text = "Hello $name!")
//}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeTestTheme {
//        VerticalContainer()
//        Container()
//        Greeting("Android")
        ButtonContainer()
    }
}