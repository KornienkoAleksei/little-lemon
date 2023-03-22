@file:OptIn(ExperimentalGlideComposeApi::class)

package com.example.littlelemon

import android.content.Context
import android.view.MenuItem
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.core.content.edit
import androidx.navigation.NavController
import androidx.room.Database
import com.bumptech.glide.Glide
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.littlelemon.ui.theme.LittleLemonGray
import com.example.littlelemon.ui.theme.LittleLemonGreen
import com.example.littlelemon.ui.theme.LittleLemonOrange
import com.example.littlelemon.ui.theme.LittleLemonYellow
import java.time.format.TextStyle

@Composable
fun Home(
    navController: NavController,
    database: List<MenuItemRoom>,
    modifier: Modifier = Modifier,
) {
    var searchPhrase by remember { mutableStateOf("") };
    val context = LocalContext.current
    val sharedPreferences by lazy {
        context.getSharedPreferences("LittleLemon", Context.MODE_PRIVATE)
    }
    Column () {
       HomeHeader(navController = navController);
        HomeHero(value = searchPhrase,
            onValueChange = { searchPhrase = it });
        Text(
            text = "ORDER FOR DELIVERY",
            style = MaterialTheme.typography.h2,
            color = Color.Black,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 24.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
            ) {
            HomeCategoryButton(label = "Starters")
            HomeCategoryButton(label = "Mains")
            HomeCategoryButton(label = "Desserts")
            HomeCategoryButton(label = "Drinks")
        }
        var menuItems: List<MenuItemRoom>;

        if (searchPhrase.isNotEmpty()) {
            menuItems = database.filter { it.title.contains(searchPhrase, ignoreCase = true) }
        } else {
            menuItems = database
        }

        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            items(
                items = menuItems,
                itemContent = { menuItem ->
                   MenuItems(menuItem = menuItem)
                }
            )
        }
    }
}

@Composable
fun HomeHeader(navController: NavController, modifier: Modifier = Modifier,){
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(96.dp),
    ){
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "little lemon logo",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .height(96.dp)
                .align(Alignment.Center)
        )
        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = "profile",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .height(48.dp)
                .padding(end = 16.dp)
                .align(Alignment.CenterEnd)
                .clickable { navController.navigate(Profile.route); }
        )
    }
}

@Composable
fun HomeHero(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val focusManager = LocalFocusManager.current;
    var focusState by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(LittleLemonGreen)
            //hide keyboard from TextField
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    focusManager.clearFocus()
                })
            }
    ) {
        Text(
            text = "Little Lemon",
            style = MaterialTheme.typography.h1,
            color = LittleLemonYellow,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .offset(y = -24.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.65f)
                    .padding(end = 16.dp)
            ) {
                Text(
                    text = "Chicago",
                    //lineHeight = 0.5.em,
                    style = MaterialTheme.typography.subtitle1,
                    color = Color.White,
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "We are a family-owned Mediterranean restaurant, focused on traditional recipes served with a modern twist",
                    style = MaterialTheme.typography.body1,
                    color = Color.White,
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
            Image(
                painter = painterResource(id = R.drawable.heroimage),
                contentDescription = "hero image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .fillMaxWidth(0.35f)
                    .aspectRatio(1f)
                    .align(Alignment.BottomEnd)
            )
        }
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            singleLine = true,
            shape = RoundedCornerShape(8.dp),
            leadingIcon = {
                Icon(imageVector = Icons.Filled.Search, contentDescription = "Search Icon")
            },
            placeholder = {
                Text(
                    text = if (focusState) "" else "Enter search phrase",
                    style = MaterialTheme.typography.button,
                )
            },

            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(
                onSearch = {
                    focusManager.clearFocus();
                }
            ),
            textStyle = MaterialTheme.typography.button,
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                .fillMaxWidth()
                .onFocusChanged { focus -> focusState = focus.isFocused; },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                disabledTextColor = Color.Gray,
                backgroundColor = LittleLemonGray,
                cursorColor = Color.Black,
                errorCursorColor = Color.Red,
                focusedBorderColor = LittleLemonGray,
                unfocusedBorderColor = LittleLemonGray,
                focusedLabelColor = Color.Black,
                unfocusedLabelColor = Color.Gray,
            )
        )
    }
}

@Composable
fun HomeCategoryButton (
    label: String,
){
    Button(
        onClick = {/*TO DO*/},
        colors = ButtonDefaults.buttonColors(
            backgroundColor = LittleLemonGray,
            disabledBackgroundColor = LittleLemonOrange,
        ),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .height(32.dp),
    ) {
        Text(
            text = label,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.button,
            color = LittleLemonGreen,
        )
    }
}

@Composable
fun  MenuItems (
    menuItem: MenuItemRoom,
    modifier: Modifier = Modifier) {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Divider(modifier = Modifier.padding(vertical = 16.dp), thickness = 1.dp, color = LittleLemonGray);
        Text(
            text = menuItem.title,
            style = MaterialTheme.typography.h3,
            color = Color.Black
        )
        Box (modifier = Modifier.fillMaxWidth()){
            GlideImage(
                model = menuItem.image,
                contentDescription = menuItem.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth(0.20f)
                    .aspectRatio(1f)
                    .align(Alignment.BottomEnd)
            )
            Text(text = menuItem.description,
                    style = MaterialTheme.typography.body1,
                color = LittleLemonGreen,
                maxLines = 2,
                modifier = Modifier
                    .fillMaxWidth(0.80f)
                    .padding(end = 16.dp)
                    .align(Alignment.TopStart)
            )
            Text(text = "$" + "%.2f".format(menuItem.price),
                style = MaterialTheme.typography.body1,
                color = LittleLemonGreen,
                maxLines = 1,
                modifier = Modifier.align(Alignment.BottomStart)
            )
        }
    }
}