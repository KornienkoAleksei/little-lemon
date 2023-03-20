package com.example.littlelemon

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.edit
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import com.example.littlelemon.ui.theme.LittleLemonGreen
import com.example.littlelemon.ui.theme.LittleLemonOrange
import com.example.littlelemon.ui.theme.LittleLemonYellow

@Composable
fun Profile(navController: NavController, modifier: Modifier = Modifier,) {
    val context = LocalContext.current
    val sharedPreferences by lazy {
        context.getSharedPreferences("LittleLemon", Context.MODE_PRIVATE)
    }
    val userLogin = sharedPreferences.getBoolean("Login", false);
    val userFirstName = sharedPreferences.getString("userFirstName", " ").orEmpty();
    val userLastName = sharedPreferences.getString("userLastName", "").orEmpty();
    val userEmail = sharedPreferences.getString("userEmail", "").orEmpty();
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
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "little lemon logo",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .height(96.dp)
                        .align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(48.dp))
                Text(
                    text = "Personal information",
                    style = MaterialTheme.typography.h3,
                    modifier = Modifier.padding(start = 16.dp)
                )
                Spacer(modifier = Modifier.height(48.dp));

                ProfileText(
                    text = "First name",
                    value = userFirstName);
                ProfileText(
                    text = "Last name",
                    value = userLastName);
                ProfileText(
                    text = "Email",
                    value = userEmail);
            }
            Button(
                onClick = {
                    Toast.makeText(
                            context,
                            "Logout",
                            Toast.LENGTH_LONG
                        ).show()
                        sharedPreferences.edit(commit = true) {
                            putBoolean("Login", false);
                            putString("userFirstName", "");
                            putString("userLastName", "");
                            putString("userEmail", "");
                        }
                        navController.navigate(Onboarding.route);
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
                    text = "Log out",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.button,
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
fun ProfileText(
    text: String,
    value: String,
    modifier: Modifier = Modifier,

    ) {
    Column() {
        Text(
            text = text,
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)
        )
        OutlinedTextField(
            value = value,
            onValueChange = {  },
            maxLines = 1,
            enabled = false,
            shape = RoundedCornerShape(8.dp),
            textStyle = MaterialTheme.typography.button,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                disabledTextColor = Color.Black,
                backgroundColor = Color.White,
                cursorColor = Color.Black,
                errorCursorColor = Color.Red,
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.Black,
                focusedLabelColor = Color.Black,
                unfocusedLabelColor = Color.Black,
            )
        )
        Spacer(modifier = Modifier.height(32.dp))
    }
}