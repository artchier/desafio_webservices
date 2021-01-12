package br.com.digitalhouse.desafiowebservice.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.digitalhouse.desafiowebservice.R
import br.com.digitalhouse.desafiowebservice.model.Results
import br.com.digitalhouse.desafiowebservice.viewmodel.MyViewModel
import kotlinx.android.synthetic.main.fragment_comic.view.*

class ComicFragment : Fragment() {
    private val myViewModel: MyViewModel by navGraphViewModels(R.id.navigation2)
    private lateinit var adapter: ComicAdapter
    private lateinit var gridLayoutManager: GridLayoutManager
    private var offset = 0
    private var listComics = arrayListOf<Results>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Atualizando os valores da lista
        myViewModel.getResults(
            0,
            15,
            "1",
            "6eb7e8896ec5850c52515a8a23ee97f0",
            "40a3aa568bb269dfad85ae0c4a297181"
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_comic, container, false)
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    findNavController().popBackStack()
                }
            })

        myViewModel._scrollCoordinates.observe(viewLifecycleOwner) {
            view.rvComic.scrollTo(it[0], it[1])
        }

        adapter = ComicAdapter(this, myViewModel, view, listComics)

        myViewModel._listComic.observe(viewLifecycleOwner) {
            if(listComics.isEmpty()){
                listComics.addAll(it)
                adapter.addComics(listComics)
                view.pbComic.visibility = View.GONE
                listComics.clear()
            }
        }

        gridLayoutManager = GridLayoutManager(view.context, 3)
        view.rvComic.adapter = adapter
        view.rvComic.layoutManager = gridLayoutManager

        setScroller(view)

        return view
    }

    private fun setScroller(view: View) {
        view.rvComic.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0) {
                    val lItem = gridLayoutManager.itemCount
                    val vItem =
                        gridLayoutManager.findFirstVisibleItemPosition()
                    val itens = adapter.itemCount
                    if (lItem + vItem >= itens) {
                        offset += 1
                        myViewModel.getResults(
                            offset * 15,
                            15,
                            "1",
                            "6eb7e8896ec5850c52515a8a23ee97f0",
                            "40a3aa568bb269dfad85ae0c4a297181"
                        )
                    }
                }
            }
        })
    }
}