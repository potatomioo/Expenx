package com.potatomioo.expenx

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import com.potatomioo.expenx.presentation.AppScreen
import com.potatomioo.expenx.ui.theme.ExpenxTheme
import java.lang.reflect.Modifier

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ExpenxTheme {
                Surface(
                    modifier = androidx.compose.ui.Modifier.fillMaxSize()
                ) {
                    AppScreen()
                }
            }
        }
    }
}

