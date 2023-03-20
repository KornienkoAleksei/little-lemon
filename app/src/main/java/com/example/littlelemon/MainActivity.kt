package com.example.littlelemon

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.MutableLiveData
import androidx.navigation.compose.rememberNavController
import com.example.littlelemon.ui.theme.LittleLemonTheme

class MainActivity : ComponentActivity() {
    private val loginLiveData = MutableLiveData<Boolean>()
    private val userFirstNameLiveData = MutableLiveData<String>()
    private val userLastNameLiveData = MutableLiveData<String>()
    private val userEmailLiveData = MutableLiveData<String>()
    private val sharedPreferences by lazy {
        getSharedPreferences("LittleLemon", MODE_PRIVATE)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        loginLiveData.value = sharedPreferences.getBoolean("Login", false)
        userFirstNameLiveData.value = sharedPreferences.getString("userFirstName", "")
        userLastNameLiveData.value = sharedPreferences.getString("userLastName", "")
        userEmailLiveData.value = sharedPreferences.getString("userEmail", "")
        setContent {
            LittleLemonTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    Navigation(navController)
                }
            }
        }
    }
}

