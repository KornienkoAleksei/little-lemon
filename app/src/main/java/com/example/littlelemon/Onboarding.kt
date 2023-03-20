package com.example.littlelemon

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.relocation.BringIntoViewRequester

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*

import androidx.compose.material.MaterialTheme.typography
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.edit
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.littlelemon.ui.theme.LittleLemonGray
import com.example.littlelemon.ui.theme.LittleLemonGreen
import com.example.littlelemon.ui.theme.LittleLemonOrange
import com.example.littlelemon.ui.theme.LittleLemonYellow
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@Composable
fun Onboarding(navController: NavController,  modifier: Modifier = Modifier, ) {
    var userFirstName by remember { mutableStateOf("") };
    var userLastName by remember { mutableStateOf("") };
    var userEmail by remember { mutableStateOf("") };
    val context = LocalContext.current
    val sharedPreferences by lazy {
        context.getSharedPreferences("LittleLemon", MODE_PRIVATE)
    }
    Surface() {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
            ) {
                OnboardingHeader();
                OnboardingOutlinedTextField(
                    text = "First name",
                    value = userFirstName,
                    onValueChange = { userFirstName = it });
                OnboardingOutlinedTextField(
                    text = "Last name",
                    value = userLastName,
                    onValueChange = { userLastName = it });
                OnboardingOutlinedTextField(
                    text = "Email",
                    value = userEmail,
                    onValueChange = { userEmail = it });
            }
            Button(
                onClick = {
                    if (userFirstName.isBlank() || userLastName.isBlank() || userEmail.isBlank()) {
                        Toast.makeText(
                            context,
                            "Registration unsuccessful. Please enter all data.",
                            Toast.LENGTH_LONG
                        ).show()
                    } else {
                        Toast.makeText(
                            context,
                            "Registration successful!",
                            Toast.LENGTH_LONG
                        ).show()
                        sharedPreferences.edit(commit = true) {
                            putBoolean("Login", true);
                            putString("userFirstName", userFirstName);
                            putString("userLastName", userLastName);
                            putString("userEmail", userEmail);
                        }
                        navController.navigate(Home.route);
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = LittleLemonYellow,
                    disabledBackgroundColor = LittleLemonOrange,
                ),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(48.dp)

            ) {
                Text(
                    text = "Register",
                    textAlign = TextAlign.Center,
                    style = typography.button,
                    color = Color.Black,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(Alignment.CenterVertically)
                )
            }
        }
    }
}

@Composable
fun OnboardingHeader(
    modifier: Modifier = Modifier,
) {
    Column() {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "little lemon logo",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .height(96.dp)
                .align(Alignment.CenterHorizontally)
        )
        Text(
            text = "Let's get to know you",
            textAlign = TextAlign.Center,
            style = typography.button,
            fontSize = 28.sp,
            color = Color.White,
            modifier = Modifier
                .background(color = LittleLemonGreen)
                .height(96.dp)
                .fillMaxWidth()
                .wrapContentHeight(Alignment.CenterVertically)
        )
        Spacer(modifier = Modifier.height(48.dp))
        Text(
            text = "Personal information",
            style = typography.h3,
            modifier = Modifier.padding(start = 16.dp)
        )
        Spacer(modifier = Modifier.height(48.dp))
    }
}

@Composable
fun OnboardingOutlinedTextField(
    text: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,

) {
    val focusManager = LocalFocusManager.current;
    Column() {
        Text(
            text = text,
            style = typography.body1,
            modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)
        )
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            maxLines = 1,
            shape = RoundedCornerShape(8.dp),
            textStyle = typography.button,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Down);
                }
            ),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                disabledTextColor = Color.Gray,
                backgroundColor = Color.White,
                cursorColor = Color.Black,
                errorCursorColor = Color.Red,
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.Gray,
                focusedLabelColor = Color.Black,
                unfocusedLabelColor = Color.Gray,
            )
        )
        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Preview (showBackground = true)
@Composable
fun OnboardingPreview(){
    Onboarding(navController = rememberNavController())
}