package com.example.recipefood.ui.screen.onboarding

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipefood.data.repository.DataStoreRepository
import com.example.recipefood.ui.navigation.Navigate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val repository: DataStoreRepository
) : ViewModel() {

    private val _isLoading: MutableState<Boolean> = mutableStateOf(true)
    val isLoading: State<Boolean> = _isLoading

    private val _startDestination: MutableState<String> = mutableStateOf(Navigate.Screen.OnBoardingWelcome.route)
    val startDestination: State<String> = _startDestination

    init {
        viewModelScope.launch {
            repository.readOnBoardingState().collect { completed ->
                println("valor completed: " + completed )
                if (completed) {
                    _startDestination.value = Navigate.Screen.Main.route
                } else {
                    _startDestination.value = Navigate.Screen.OnBoardingWelcome.route
                }
            }

            delay(5000)
            _isLoading.value = false
        }
    }

}