package com.thoughtworks.training.reply.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import com.thoughtworks.training.reply.ui.theme.ContrastAwareReplyTheme

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            ContrastAwareReplyTheme(darkTheme = isSystemInDarkTheme()) {
                ReplyApp()
            }
        }
    }
}