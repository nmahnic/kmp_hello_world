package com.nicomahnic.helloworldkmp.api

import com.nicomahnic.helloworldkmp.api.network.ApiUiState
import com.nicomahnic.helloworldkmp.api.network.Character
import com.nicomahnic.helloworldkmp.api.network.NetworkUtils
import com.nicomahnic.helloworldkmp.api.network.ScreenState
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ApiViewModel : ViewModel() {
    private val _uiState: MutableStateFlow<ApiUiState> = MutableStateFlow(ApiUiState())
    val uiState = _uiState.asStateFlow()

    override fun onCleared() {
        NetworkUtils.httpClient.close()
    }

    init {
        fetchCharacters()
    }

    private fun fetchCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val characters = NetworkUtils.httpClient.get("Characters").body<List<Character>>()
                _uiState.value = ApiUiState(
                    screenState = ScreenState.SUCCESS,
                    characters = characters
                )
            } catch (e: Exception) {
                _uiState.value = ApiUiState(
                    screenState = ScreenState.FAILURE
                )
            }
        }
    }
}