package com.example.littlelemon.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.littlelemon.R

val Karla = FontFamily(
    Font(R.font.karla_regular)
)
val MarkaziText = FontFamily(
    Font(R.font.markazitext_regular)
)

// Set of Material typography styles to start with
val Typography = Typography(
    h1 = TextStyle(
        fontFamily = MarkaziText,
        fontWeight = FontWeight.Medium,
        fontSize = 64.sp
    ),
    h2 = TextStyle(
        fontFamily = Karla,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 20.sp
    ),
    h3 = TextStyle(
        fontFamily = Karla,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp
    ),
    subtitle1 = TextStyle(
        fontFamily = MarkaziText,
        fontWeight = FontWeight.Normal,
        fontSize = 40.sp
    ),
    subtitle2 = TextStyle(
        fontFamily = Karla,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 16.sp
    ),
    button = TextStyle(
        fontFamily = Karla,
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp
    ),
    body1 = TextStyle(
        fontFamily = Karla,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp
    ),
    body2 = TextStyle(
        fontFamily = Karla,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp
    )

    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)


/*
h1: TextStyle,
    h2: TextStyle,
    h3: TextStyle,
    h4: TextStyle,
    h5: TextStyle,
    h6: TextStyle,
subtitle1: TextStyle,
    subtitle2: TextStyle,
    body1: TextStyle,
    body2: TextStyle,
button: TextStyle,
    caption: TextStyle,
    overline: TextStyle
 */