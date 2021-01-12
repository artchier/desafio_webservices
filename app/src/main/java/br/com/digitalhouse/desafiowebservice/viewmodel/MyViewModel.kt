package br.com.digitalhouse.desafiowebservice.viewmodel

import android.app.Application
import android.os.Parcelable
import android.util.Log
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import br.com.digitalhouse.desafiowebservice.R
import br.com.digitalhouse.desafiowebservice.model.Results
import br.com.digitalhouse.desafiowebservice.service.repository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*

class MyViewModel(application: Application) : AndroidViewModel(application) {
    fun initSplashScreen(view: View) {
        viewModelScope.launch {
            delay(50)
            view.findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
        }
    }

    var _listComic = MutableLiveData<ArrayList<Results>>()
    private val listComic: MutableLiveData<ArrayList<Results>>
        get() = _listComic

    var _parametersComic = MutableLiveData<ArrayList<String>>()
    private val parametersComic: MutableLiveData<ArrayList<String>>
        get() = _parametersComic

    var _scrollCoordinates = MutableLiveData<IntArray>()
    private val scrollCoordinates: MutableLiveData<IntArray>
        get() = _scrollCoordinates

    fun getResults(p1: Int, p2: Int, p3: String, p4: String, p5: String) {
        viewModelScope.launch {
            val res = repository.getResults(p1, p2, p3, p4, p5)
            Log.i("MyViewModel", res.toString())
            listComic.value = res.data.results
        }
    }

    fun addParameters(parameters: ArrayList<String>) {
        parametersComic.value = parameters
    }

    fun updateScrollCoordinates(coordinates: IntArray) {
        scrollCoordinates.value = coordinates
    }
}