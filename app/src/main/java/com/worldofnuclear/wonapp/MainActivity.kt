package com.worldofnuclear.wonapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.worldofnuclear.wonapp.ui.WorldOfNuclearApp
import com.worldofnuclear.wonapp.ui.theme.WoNAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WoNAppTheme {
                WorldOfNuclearApp()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewWorldOfNuclearApp() {
    WoNAppTheme {
        WorldOfNuclearApp()
    }
}
