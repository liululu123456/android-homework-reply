package com.thoughtworks.training.reply.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import com.thoughtworks.training.reply.ui.theme.ContrastAwareReplyTheme

class MainActivity : ComponentActivity() {

    private val viewModel: ReplyHomeViewModel by viewModels()

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            ContrastAwareReplyTheme {
                // TODO
                ReplyApp(
                    displayFeatures = emptyList(),
                    replyHomeUIState = ReplyHomeUIState(),
                    closeDetailScreen = { /* 实现关闭详细屏幕的逻辑 */ },
                    navigateToDetail = { id, type -> /* 实现导航到详细页面的逻辑 */ },
                    toggleSelectedEmail = { id -> /* 实现切换选定电子邮件的逻辑 */ }
                )
            }
        }
    }
}