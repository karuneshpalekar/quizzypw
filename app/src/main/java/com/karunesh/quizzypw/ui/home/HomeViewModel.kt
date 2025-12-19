package com.karunesh.quizzypw.ui.home


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.karunesh.quizzypw.data.model.HomeResponse
import com.karunesh.quizzypw.data.remote.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository
) : ViewModel() {

    private val _homeData = MutableLiveData<HomeResponse>()
    val homeData: LiveData<HomeResponse> = _homeData

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun loadHomeData() {
        viewModelScope.launch {
            try {
                _homeData.value = repository.getHomeData()
            } catch (e: Exception) {
                _error.value = "Something went wrong. Please try again later."
            }
        }
    }
}