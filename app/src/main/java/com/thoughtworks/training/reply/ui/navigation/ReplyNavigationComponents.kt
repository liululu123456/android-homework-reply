package com.thoughtworks.training.reply.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Article
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Inbox
import androidx.compose.material.icons.filled.Message
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import com.thoughtworks.training.reply.ui.utils.ReplyNavigationContentPosition

@Composable
fun ReplyNavigationRail(
    selectedDestination: String,
    navigationContentPosition: ReplyNavigationContentPosition,
    onDrawerClicked: (String) -> Unit,
) {
    NavigationBar {
        NavigationBarItem(
            selected = selectedDestination == ReplyRoute.INBOX,
            onClick = { onDrawerClicked(ReplyRoute.INBOX) },
            icon = { Icon(Icons.Default.Inbox, contentDescription = "Inbox") }
        )
        NavigationBarItem(
            selected = selectedDestination == ReplyRoute.DM,
            onClick = { onDrawerClicked(ReplyRoute.DM) },
            icon = { Icon(Icons.Default.Message, contentDescription = "Inbox") }
        )
        NavigationBarItem(
            selected = selectedDestination == ReplyRoute.ARTICLES,
            onClick = { onDrawerClicked(ReplyRoute.ARTICLES) },
            icon = { Icon(Icons.Default.Article, contentDescription = "Inbox") }
        )
        NavigationBarItem(
            selected = selectedDestination == ReplyRoute.GROUPS,
            onClick = { onDrawerClicked(ReplyRoute.GROUPS) },
            icon = { Icon(Icons.Default.Group, contentDescription = "Inbox") }
        )
    }
}

@Composable
fun PermanentNavigationDrawerContent(
    selectedDestination: String,
    navigationContentPosition: ReplyNavigationContentPosition,
    navigateToTopLevelDestination: (ReplyTopLevelDestination) -> Unit,
) {
    // TODO
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalNavigationDrawerContent(
    selectedDestination: String,
    navigationContentPosition: ReplyNavigationContentPosition,
    navigateToTopLevelDestination: (ReplyTopLevelDestination) -> Unit,
    onDrawerClicked: () -> Unit = {}
) {
    // TODO
}

enum class LayoutType {
    HEADER, CONTENT
}
