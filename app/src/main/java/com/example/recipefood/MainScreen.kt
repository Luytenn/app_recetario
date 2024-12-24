package com.example.recipefood

import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.recipefood.ui.navigation.SetupNavGraph
import com.example.recipefood.ui.screen.onboarding.SplashViewModel
import com.example.recipefood.ui.screen.onboarding.WelcomeScreen
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.pager.ExperimentalPagerApi

@OptIn(ExperimentalAnimationApi::class, ExperimentalPagerApi::class)
@Composable
 fun MainScreen(splashViewModel: SplashViewModel = hiltViewModel()
                ,onLauncherFinished : () -> Unit) {

    val navController = rememberAnimatedNavController()
    val screen by splashViewModel.startDestination

    SetupNavGraph(navController = navController, startDestination = screen)
    onLauncherFinished()

}