package com.nicomahnic.helloworldkmp.api.network

enum class ScreenState {
    LOADING,
    SUCCESS,
    FAILURE
}

data class ApiUiState(
    val screenState: ScreenState = ScreenState.LOADING,
    val characters: List<Character> = listOf()
)