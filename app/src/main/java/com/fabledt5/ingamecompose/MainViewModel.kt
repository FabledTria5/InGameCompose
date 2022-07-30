package com.fabledt5.ingamecompose

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fabledt5.domain.use_case.authentication.IsAuthenticated
import com.fabledt5.domain.use_case.collections.UpdateSavedGames
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val isAuthenticated: IsAuthenticated,
    private val updateSavedGames: UpdateSavedGames
) : ViewModel() {

    fun startUpdateSavedGames() {
        if (isAuthenticated()) updateSavedGames()
            .flowOn(Dispatchers.IO)
            .launchIn(viewModelScope)
    }

}