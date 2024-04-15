package com.thoughtworks.training.reply.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.window.layout.DisplayFeature
import com.thoughtworks.training.reply.data.EmailsRepository
import com.thoughtworks.training.reply.data.EmailsRepositoryImpl
import com.thoughtworks.training.reply.ui.navigation.ReplyNavigationRail
import com.thoughtworks.training.reply.ui.navigation.ReplyRoute
import com.thoughtworks.training.reply.ui.navigation.ReplyTopLevelDestination
import com.thoughtworks.training.reply.ui.utils.ReplyContentType
import com.thoughtworks.training.reply.ui.utils.ReplyNavigationContentPosition
import com.thoughtworks.training.reply.ui.utils.ReplyNavigationType

@Composable
fun ReplyApp(
    displayFeatures: List<DisplayFeature>,
    replyHomeUIState: ReplyHomeUIState,
    closeDetailScreen: () -> Unit = {},
    navigateToDetail: (Long, ReplyContentType) -> Unit = { _, _ -> },
    toggleSelectedEmail: (Long) -> Unit = { }
) {

    val navController = rememberNavController()
    val selectedTab = remember { mutableStateOf(ReplyRoute.INBOX) }
    val repository: EmailsRepository = EmailsRepositoryImpl()
    val viewModel = ReplyHomeViewModel(repository)

    Scaffold(
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
            navigationType = ReplyNavigationType.BOTTOM_NAVIGATION,
            contentType = ReplyContentType.SINGLE_PANE,
            navController = navController,
            displayFeatures = displayFeatures,
            replyHomeUIState = replyHomeUIState,
            closeDetailScreen = closeDetailScreen,
            navigateToDetail = navigateToDetail,
            toggleSelectedEmail = toggleSelectedEmail,
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
fun ReplyAppContent(
    modifier: Modifier = Modifier,
    navigationType: ReplyNavigationType,
    contentType: ReplyContentType,
    displayFeatures: List<DisplayFeature>,
    navigationContentPosition: ReplyNavigationContentPosition,
    replyHomeUIState: ReplyHomeUIState,
    navController: NavHostController,
    selectedDestination: String,
    navigateToTopLevelDestination: (ReplyTopLevelDestination) -> Unit,
    closeDetailScreen: () -> Unit,
    navigateToDetail: (Long, ReplyContentType) -> Unit,
    toggleSelectedEmail: (Long) -> Unit,
    onDrawerClicked: () -> Unit = {}
) {
     // TODO
}

@Composable
private fun ReplyNavHost(
    navController: NavHostController,
    contentType: ReplyContentType,
    displayFeatures: List<DisplayFeature>,
    replyHomeUIState: ReplyHomeUIState,
    navigationType: ReplyNavigationType,
    closeDetailScreen: () -> Unit,
    navigateToDetail: (Long, ReplyContentType) -> Unit,
    toggleSelectedEmail: (Long) -> Unit,
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
