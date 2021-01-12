package br.com.digitalhouse.desafiowebservice.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import br.com.digitalhouse.desafiowebservice.R
import br.com.digitalhouse.desafiowebservice.viewmodel.MyViewModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_detail.view.*

class DetailFragment : Fragment() {
    private val myViewModel: MyViewModel by navGraphViewModels(R.id.navigation2)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_detail, container, false)

        view.tbDetail.setupWithNavController(
            findNavController(),
            AppBarConfiguration(findNavController().graph)
        )
        view.tbDetail.navigationIcon = ResourcesCompat.getDrawable(resources, R.drawable.back, null)

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    findNavController().popBackStack()
                }
            })

        myViewModel._parametersComic.observe(viewLifecycleOwner) {
            view.tvDetail.text = it[0]
            view.tvDescription.text = it[1]
            view.tvPublishedValue.text = if (it[2] == "0") "No date available" else it[2]
            view.tvPriceValue.text = if (it[3] == "0") "" else it[3]
            view.tvPagesValue.text = it[4]
            Glide.with(this).load(it[5]).into(view.ivThumbnail)
            if (it[6] != "") Glide.with(this).load(it[6]).into(view.ivPromoArt)
        }

        view.ivThumbnail.setOnClickListener {
            findNavController().navigate(R.id.action_detailFragment_to_thumbnailFragment)
        }

        return view
    }
}