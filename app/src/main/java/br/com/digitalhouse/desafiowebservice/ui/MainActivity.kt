package br.com.digitalhouse.desafiowebservice.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import br.com.digitalhouse.desafiowebservice.R
import br.com.digitalhouse.desafiowebservice.viewmodel.MyViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var myViewModel: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myViewModel = ViewModelProvider(this).get(MyViewModel::class.java)
    }

    companion object{
        var fromBackStack = false
    }
}