package com.example.littlelemon

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.edit
import androidx.navigation.NavController
import com.example.littlelemon.ui.theme.LittleLemonGreen
import com.example.littlelemon.ui.theme.LittleLemonOrange
import com.example.littlelemon.ui.theme.LittleLemonYellow

@Composable
fun Home( navController: NavController, modifier: Modifier = Modifier,) {
    val context = LocalContext.current
    val sharedPreferences by lazy {
        context.getSharedPreferences("LittleLemon", Context.MODE_PRIVATE)
    }
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
            text = "Home page",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.button,
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
            style = MaterialTheme.typography.h3,
            modifier = Modifier.padding(start = 16.dp)
        )
        Spacer(modifier = Modifier.height(48.dp))


        Button(
            onClick = {
                navController.navigate(Profile.route);
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
                style = MaterialTheme.typography.button,
                color = Color.Black,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(Alignment.CenterVertically)
            )
        }
    }

}

@Composable
fun HomeHeader(navController: NavController, modifier: Modifier = Modifier,){


}