package com.thoughtworks.training.reply.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.thoughtworks.training.reply.data.EmailsRepository
import com.thoughtworks.training.reply.data.EmailsRepositoryImpl
import com.thoughtworks.training.reply.ui.navigation.ReplyNavigationRail
import com.thoughtworks.training.reply.ui.navigation.ReplyRoute
import com.thoughtworks.training.reply.ui.theme.tertiaryContainerLight
import com.thoughtworks.training.reply.ui.utils.ReplyNavigationContentPosition

@Composable
fun ReplyApp(
) {

    val navController = rememberNavController()
    val selectedTab = remember { mutableStateOf(ReplyRoute.INBOX) }
    val repository: EmailsRepository = EmailsRepositoryImpl()
    val viewModel = ReplyHomeViewModel(repository)

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(24.dp)),
                onClick = {},
                containerColor = tertiaryContainerLight

            ) {
                Icon(Icons.Default.Edit, contentDescription = "edit")
            }
        },
        bottomBar = {
            ReplyNavigationWrapper(
                navigationContentPosition = ReplyNavigationContentPosition.CENTER,
                selectedTab = selectedTab,
                onTabSelected = { route ->
                    selectedTab.value = route
                    navController.navigate(route) },
                )
        }
    ) {
        innerPadding ->
        ReplyNavHost(
            navController = navController,
            modifier = Modifier.padding(innerPadding),
            viewModel = viewModel
        )
    }
}

@Composable
private fun ReplyNavigationWrapper(
    navigationContentPosition: ReplyNavigationContentPosition,
    selectedTab: MutableState<String>,
    onTabSelected: (String) -> Unit,
) {
    ReplyNavigationRail (
        selectedDestination = selectedTab.value,
        navigationContentPosition = navigationContentPosition,
        onDrawerClicked = onTabSelected,
    )
}

@Composable
private fun ReplyNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: ReplyHomeViewModel
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = ReplyRoute.INBOX,
    ) {
        composable(ReplyRoute.INBOX) {
            ReplyInboxScreen(
                viewModel = viewModel
            )
        }
        composable(ReplyRoute.DM) {
            EmptyComingSoon()
        }
        composable(ReplyRoute.ARTICLES) {
            EmptyComingSoon()
        }
        composable(ReplyRoute.GROUPS) {
            EmptyComingSoon()
        }
    }
}
