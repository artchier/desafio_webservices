package br.com.digitalhouse.desafiowebservice.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import br.com.digitalhouse.desafiowebservice.R
import br.com.digitalhouse.desafiowebservice.viewmodel.SplashViewModel

class SplashFragment : Fragment() {
    private lateinit var splashViewModel: SplashViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)

        splashViewModel = ViewModelProvider(this).get(SplashViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_splash, container, false)
        splashViewModel.initSplashScreen(view)
        /*findNavController()
            .navigate(R.id.action_splashFragment_to_loginFragment)*/
        return view
    }
}