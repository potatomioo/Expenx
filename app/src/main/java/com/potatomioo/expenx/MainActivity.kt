package com.potatomioo.expenx

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.potatomioo.expenx.presentation.AppScreen
import com.potatomioo.expenx.ui.theme.ExpenxTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ExpenxTheme {
                AppScreen()
            }
        }
    }
}

