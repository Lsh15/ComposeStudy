package com.example.composetest

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
//                    TextFieldTest()
                    NavigationGraph()
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

    val pressedState = if (isPressed) "?????? click" else "?????? no click"

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
            Text(text = "?????? 1")
       }
        Button(
            elevation = ButtonDefaults.elevation(
                defaultElevation = 20.dp,
                pressedElevation = 0.dp,
                disabledElevation = 0.dp
            ),
            enabled = true,
            onClick = {
                Log.d("TAG","ButtonContainer : ?????? 2 Click")
            }) {
            Text(text = "?????? 2")
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
                Log.d("TAG","ButtonContainer : ?????? 3 Click")
            }) {
            Text(text = "?????? 3")
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
                Log.d("TAG","ButtonContainer : ?????? 4 Click")
            }) {
            Text(text = "?????? 4")
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
                Log.d("TAG","ButtonContainer : ?????? 5 Click")
            }) {
            Text(text = "?????? 5", color = Color.White)
        }
//        if (isPressed) Text(text = "?????? click") else Text(text = "?????? no click")
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
                Log.d("TAG","ButtonContainer : ?????? 6 Click")
            }) {
            Text(text = "?????? 6", color = Color.White)
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

@Composable
fun CheckBoxContainer(){

    val checkedStatusForFirst = remember { mutableStateOf(false) }
    val checkedStatusForSecond = remember { mutableStateOf(false) }
    val checkedStatusForThird = remember { mutableStateOf(false) }
//    val checkedStatusForForth = remember { mutableStateOf(false) }

    val checkedStatesArray = listOf(
        checkedStatusForFirst,
        checkedStatusForSecond,
        checkedStatusForThird,
    )

    val allBoxChecked: (Boolean) -> Unit = { isAllBoxChecked ->
        Log.d("TAG", "CheckBoxContainer: isAllBoxChecked : $isAllBoxChecked")
        checkedStatesArray.forEach { it.value = isAllBoxChecked }
    }

//    val checkedStatusForForth : Boolean = checkedStatesArray.all { it.value == true }
    val checkedStatusForForth : Boolean = checkedStatesArray.all { it.value }

//    var checkedStatusForSecond by remember { mutableStateOf(false) }
//
//    var (checkedStatusForThird, setCheckedStatusForThird) = remember { mutableStateOf(false) }

    var (checkedStatusForFourth, setCheckedStatusForFourth) = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        CheckBoxWithTitle("1??? ????????????", checkedStatusForFirst)
        CheckBoxWithTitle("2??? ????????????", checkedStatusForSecond)
        CheckBoxWithTitle("3??? ????????????", checkedStatusForThird)

//        Checkbox(
//            enabled = true,
//            checked = checkedStatusForSecond,
//            onCheckedChange = { isChecked ->
//                Log.d("TAG", "CheckBoxContainer: isChecked: $isChecked")
//                checkedStatusForSecond = isChecked
//            })
//        Checkbox(
//            enabled = true,
//            checked = checkedStatusForThird,
//            onCheckedChange = {
//                Log.d("TAG", "CheckBoxContainer: isChecked: $it")
//                setCheckedStatusForThird.invoke(it)
//            })
        Spacer(modifier = Modifier.height(10.dp))
        AllAgreeCheckBox("?????? ???????????????????", checkedStatusForForth, allBoxChecked)
        Spacer(modifier = Modifier.height(10.dp))
        MyCustomCheckBox(title = "????????? ???????????? ?????? O", withRipple = true)
        MyCustomCheckBox(title = "????????? ???????????? ?????? X", withRipple = false)
//        Checkbox(
//            enabled = true,
//            checked = checkedStatusForFourth,
//            colors = CheckboxDefaults.colors(
//                checkedColor = Color.Red,
//                uncheckedColor = Color(0xFFEF9A9A),
//                checkmarkColor = Color.Black,
//                disabledColor = Color(0xFF90CAF9)
//            ),
//            onCheckedChange = {
//                Log.d("TAG", "CheckBoxContainer: isChecked: $it")
//                setCheckedStatusForFourth.invoke(it)
//            })
    }
}

@Composable
fun CheckBoxWithTitle(title: String, isCheckedState: MutableState<Boolean>) {
    Row(
        modifier = Modifier
//            .background(Color.Yellow)
            .padding(horizontal = 30.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Checkbox(
            enabled = true,
            checked = isCheckedState.value,
            onCheckedChange = { isChecked ->
                Log.d("TAG", "CheckBoxContainer: isChecked: $isChecked")
                isCheckedState.value = isChecked
            })
        Text(text = title)
    }
}

@Composable
fun AllAgreeCheckBox(title: String,
                     shouldChecked: Boolean ,
                     allBoxChecked: (Boolean) -> Unit) {
    Row(
        modifier = Modifier
//            .background(Color.Yellow)
            .padding(horizontal = 30.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Checkbox(
            enabled = true,
            checked = shouldChecked,
            colors = CheckboxDefaults.colors(
                checkedColor = Color.Red,
                uncheckedColor = Color(0xFFEF9A9A),
                checkmarkColor = Color.White,
                disabledColor = Color(0xFF90CAF9)
            ),
            onCheckedChange = { isChecked ->
                Log.d("TAG", "CheckBoxContainer: isChecked: $isChecked")
//                isCheckedState.value = isChecked
                allBoxChecked(isChecked)
            })
        Text(text = title)
    }
}

@Composable
fun MyCustomCheckBox(title: String, withRipple: Boolean = false){

//    var isCheckedState by remember { mutableStateOf(false) }
//    var isChecked = remember { mutableStateOf(false) }
    var (isChecked, setIsChecked) = remember { mutableStateOf(false) }

    var togglePainter = if (isChecked == true) R.drawable.ic_checked else R.drawable.ic_unchecked

    var checkedInfoString = if (isChecked) "?????????" else "????????????"

    var rippleEffect = if (withRipple) rememberRipple(
        radius = 30.dp,
        bounded = false,
        color = Color.Blue
    ) else null

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
//            .background(Color.Yellow)
            .padding(horizontal = 30.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(60.dp)
//            .background(Color.Yellow)
                .clickable(
                    indication = rippleEffect,
                    interactionSource = remember { MutableInteractionSource() }
                ) {
                    setIsChecked.invoke(!isChecked)
                    Log.d("TAG", "MyCustomCheckBox: ????????? ?????????! / $isChecked")
                }){
            Image(
                painter = painterResource(id = togglePainter),
                contentDescription = null
            )
        }
        Text(text = "$title / $checkedInfoString")
    }
}

@Composable
fun TextFieldTest(){

    var userInput by remember { mutableStateOf(TextFieldValue()) }

    var phoneNumberInput by remember { mutableStateOf(TextFieldValue()) }

    var emailInput by remember { mutableStateOf(TextFieldValue()) }

    val shouldShowPassword = remember { mutableStateOf(false) }

    var passwordInput by remember { mutableStateOf(TextFieldValue()) }

    val passwordResource: (Boolean) -> Int = {
        if(it) {
            R.drawable.ic_baseline_visibility_24
        } else {
            R.drawable.ic_baseline_visibility_off_24
        }
    }

    Column(
        Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = userInput,
            singleLine = false,
            maxLines = 2,
            onValueChange = { newValue -> userInput = newValue },
            label = { Text("????????? ??????") },
            placeholder = { Text("????????? ?????????") }
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = phoneNumberInput,
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            onValueChange = { newValue -> phoneNumberInput = newValue },
            label = { Text("????????????") },
            placeholder = { Text("010-1234-1234") }
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = emailInput,
            singleLine = true,
            leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = null) },
//            trailingIcon = { Icon(imageVector = Icons.Default.CheckCircle, contentDescription = null) },
            trailingIcon = { IconButton(onClick = { Log.d("TAG", "TextFieldTest: ???????????? ??????") }) {
                Icon(imageVector = Icons.Default.CheckCircle, contentDescription = null)
            }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            onValueChange = { newValue -> emailInput = newValue },
            label = { Text("????????? ??????") },
            placeholder = { Text("????????? ????????? ????????? ?????????") }
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = passwordInput,
            singleLine = true,
            leadingIcon = { Icon(imageVector = Icons.Default.Person, contentDescription = null) },
//            trailingIcon = { Icon(imageVector = Icons.Default.CheckCircle, contentDescription = null) },
            trailingIcon = { IconButton(onClick = {
                Log.d("TAG", "TextFieldTest: ???????????? visible ?????? ??????")
                shouldShowPassword.value = !shouldShowPassword.value
            }) {
                Icon(painter = painterResource(id = passwordResource(shouldShowPassword.value)), contentDescription = null)
            }
            },
            visualTransformation = if (shouldShowPassword.value) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            onValueChange = { newValue -> passwordInput = newValue },
            label = { Text("????????????") },
            placeholder = { Text("??????????????? ??????????????????") }
        )
    }
}

// ??????????????? ????????? ?????? (?????? ????????? ??????)
enum class NAV_ROUTE(val routeName: String, val description: String, val btnColor: Color){
    MAIN("MAIN", "?????? ??????", Color(0xFF3949AB)),
    LOGIN("LOGIN", "????????? ??????", Color(0xFF5E35B1)),
    REGISTER("REGISTER", "???????????? ??????", Color(0xFFD81B60)),
    USER_PROFILE("USER_PROFILE", "?????? ????????? ??????", Color(0xFF00897B)),
    SETTING("SETTING", "?????? ??????", Color(0xFFF4511E))
}

// ??????????????? ????????? ??????
class RouteAction(navHostController: NavHostController) {

    //???????????? ??????
    val navTo: (NAV_ROUTE) -> Unit = { route ->
        navHostController.navigate(route.routeName)
    }

    // ???????????? ??????
    val goBack: () -> Unit = {
        navHostController.navigateUp()
    }
}

@Composable
fun NavigationGraph(startRoute: NAV_ROUTE = NAV_ROUTE.MAIN){
    // ??????????????? ????????????
    val navController = rememberNavController()

    // ??????????????? ????????? ??????
    val routeAction = remember(navController) { RouteAction(navController) }

    // NavHost ??? ??????????????? ??????
    // ??????????????? ????????? ???????????? ????????????
    NavHost(navController, startRoute.routeName){
        // ????????? ?????? = ????????? ???
        composable(NAV_ROUTE.MAIN.routeName){
            // ?????? = ???
            MainScreen(routeAction = routeAction)
        }
        // ????????? ?????? = ????????? ???
        composable(NAV_ROUTE.LOGIN.routeName){
            // ?????? = ???
            LoginScreen(routeAction = routeAction)
        }
        // ????????? ?????? = ????????? ???
        composable(NAV_ROUTE.REGISTER.routeName){
            // ?????? = ???
            RegisterScreen(routeAction = routeAction)
        }
        // ????????? ?????? = ????????? ???
        composable(NAV_ROUTE.USER_PROFILE.routeName){
            // ?????? = ???
            UserProfileScreen(routeAction = routeAction)
        }
        // ????????? ?????? = ????????? ???
        composable(NAV_ROUTE.SETTING.routeName){
            // ?????? = ???
            SettingScreen(routeAction = routeAction)
        }
    }
}

// ?????? ??????
@Composable
fun MainScreen(routeAction: RouteAction){
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(Modifier.padding(16.dp)) {
            NavButton(route = NAV_ROUTE.LOGIN, routeAction = routeAction)
            NavButton(route = NAV_ROUTE.REGISTER, routeAction = routeAction)
            NavButton(route = NAV_ROUTE.USER_PROFILE, routeAction = routeAction)
            NavButton(route = NAV_ROUTE.SETTING, routeAction = routeAction)
        }
    }
}
// ????????? ??????
@Composable
fun LoginScreen(routeAction: RouteAction){
    Surface(Modifier.fillMaxSize()) {
        Box(Modifier.padding(8.dp), Alignment.Center){
            Text(text = "????????? ??????", style = TextStyle(Color.Black, 22.sp, FontWeight.Medium))
            // ???????????? ??????
            Button(onClick = routeAction.goBack,
                modifier = Modifier
                    .padding(16.dp)
                    .offset(y = 100.dp)) {
                Text("????????????")
            }
        }
    }
}

// ???????????? ??????
@Composable
fun RegisterScreen(routeAction: RouteAction){
    Surface(Modifier.fillMaxSize()) {
        Box(Modifier.padding(8.dp), Alignment.Center){
            Text(text = "???????????? ??????", style = TextStyle(Color.Black, 22.sp, FontWeight.Medium))
            // ???????????? ??????
            Button(onClick = routeAction.goBack,
                modifier = Modifier
                    .padding(16.dp)
                    .offset(y = 100.dp)) {
                Text("????????????")
            }
        }
    }
}

// ?????? ????????? ??????
@Composable
fun UserProfileScreen(routeAction: RouteAction){
    Surface(Modifier.fillMaxSize()) {
        Box(Modifier.padding(8.dp), Alignment.Center){
            Text(text = "?????? ????????? ??????", style = TextStyle(Color.Black, 22.sp, FontWeight.Medium))
            // ???????????? ??????
            Button(onClick = routeAction.goBack,
                modifier = Modifier
                    .padding(16.dp)
                    .offset(y = 100.dp)) {
                Text("????????????")
            }
        }
    }
}

// ?????? ??????
@Composable
fun SettingScreen(routeAction: RouteAction){
    Surface(Modifier.fillMaxSize()) {
        Box(Modifier.padding(8.dp), Alignment.Center){
            Text(text = "?????? ??????", style = TextStyle(Color.Black, 22.sp, FontWeight.Medium))
            // ???????????? ??????
            Button(onClick = routeAction.goBack,
                modifier = Modifier
                    .padding(16.dp)
                    .offset(y = 100.dp)) {
                Text("????????????")
            }
        }
    }
}

// ????????? ?????? ??????????????? ??????
@Composable
fun ColumnScope.NavButton(route: NAV_ROUTE, routeAction: RouteAction){
    Button(onClick = {
        routeAction.navTo(route)
    },colors = ButtonDefaults.buttonColors(backgroundColor = route.btnColor),
        modifier = Modifier
            .weight(1f)
            .padding(8.dp)
            .fillMaxSize()
    ) {
        Text(text = route.description,
            style = TextStyle(Color.White, 22.sp, FontWeight.Medium)
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeTestTheme {
//        VerticalContainer()
//        Container()
        NavigationGraph()
    }
}