package br.com.digitalhouse.desafiowebservice.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import br.com.digitalhouse.desafiowebservice.R
import kotlinx.android.synthetic.main.fragment_register.view.*

class RegisterFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_register, container, false)
        view.tbRegister.setupWithNavController(findNavController(), AppBarConfiguration(findNavController().graph))
        /*view.tbRegister.title = resources.getString(R.string.register)*/
        return view
    }
}