package br.com.digitalhouse.desafiowebservice.viewmodel

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import br.com.digitalhouse.desafiowebservice.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel(application: Application): AndroidViewModel(application) {
    val application = getApplication<Application>().applicationContext
    /*var liveData: MutableLiveData<Splash>*/

    fun initSplashScreen(view: View) {
        viewModelScope.launch {
            delay(2000)
            view.findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
        }
    }

}