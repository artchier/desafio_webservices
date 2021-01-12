package br.com.digitalhouse.desafiowebservice.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import br.com.digitalhouse.desafiowebservice.R
import br.com.digitalhouse.desafiowebservice.viewmodel.MyViewModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_detail.view.*
import kotlinx.android.synthetic.main.fragment_thumbnail.view.*

class ThumbnailFragment : Fragment() {
    private val myViewModel: MyViewModel by navGraphViewModels(R.id.navigation2)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_thumbnail, container, false)

        view.tbThumbnailBigger.setupWithNavController(
            findNavController(),
            AppBarConfiguration(findNavController().graph)
        )
        view.tbThumbnailBigger.navigationIcon =
            ResourcesCompat.getDrawable(resources, R.drawable.close, null)

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    findNavController().popBackStack()
                }
            })

        myViewModel._parametersComic.observe(viewLifecycleOwner) {
            Glide.with(this).load(it[5]).into(view.ivThumbnailBigger)
        }

        return view
    }
}