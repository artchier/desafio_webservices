package br.com.digitalhouse.desafiowebservice.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import br.com.digitalhouse.desafiowebservice.R
import br.com.digitalhouse.desafiowebservice.model.Results
import br.com.digitalhouse.desafiowebservice.viewmodel.MyViewModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_comic.view.*
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

class ComicAdapter(
    private val context: ComicFragment,
    private val myViewModel: MyViewModel,
    private val inflate: View,
    private var listComics: ArrayList<Results>
) :

    RecyclerView.Adapter<ComicAdapter.ComicViewHolder>() {
    class ComicViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivCover: ImageView = view.findViewById(R.id.ivCover)
        val tvNumber: TextView = view.findViewById(R.id.tvNumber)
    }

    private lateinit var title: String
    private lateinit var description: String
    private lateinit var date: Date
    private var price: Double = 0.0
    private var pageCount: Int = 0
    private lateinit var thumbnail: String
    private lateinit var promoArt: String

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.comic_item, parent, false)

        return ComicViewHolder(view)
    }

    override fun getItemCount(): Int = listComics.size

    fun addComics(results: ArrayList<Results>) {
        listComics.addAll(results)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ComicViewHolder, position: Int) {
        val comics = listComics[position]
        var number: String? = null
        try {
            val hashCoordinates: IntArray = findHash(comics.title)
            number = StringBuilder(comics.title).substring(hashCoordinates[0], hashCoordinates[1])
        } catch (ignored: Exception) {
        }

        Glide.with(context)
            .load("${comics.thumbnail.path}.${comics.thumbnail.extension}")
            .into(holder.ivCover)

        holder.tvNumber.text = number

        holder.itemView.setOnClickListener {
            title = when (comics.title) {
                null -> "No title available"
                else -> comics.title
            }

            description = when (comics.description) {
                null -> "No description available"
                else -> comics.description
            }

            date = try {
                SimpleDateFormat(
                    "yyyy-MM-dd'T'HH:mm:ss",
                    Locale.ENGLISH
                ).parse(comics.dates[0].date)!!
            } catch (exception: Exception) {
                Date(0)
            }

            price = comics.prices[0].price

            pageCount = comics.pageCount

            thumbnail = "${comics.thumbnail.path}.${comics.thumbnail.extension}"

            promoArt = try {
                "${comics.images[0].path}.${comics.images[0].extension}"
            } catch (ignored: Exception) {
                ""
            }

            myViewModel.addParameters(
                arrayListOf(
                    title,
                    description.replace("<br>", ""),
                    SimpleDateFormat("MMMM dd, yyyy", Locale.ENGLISH).format(date),
                    NumberFormat.getCurrencyInstance(Locale.US).format(price),
                    pageCount.toString(),
                    thumbnail,
                    promoArt
                )
            )

            myViewModel.updateScrollCoordinates(
                intArrayOf(
                    inflate.rvComic.scrollX,
                    inflate.rvComic.scrollY
                )
            )
            findNavController(context).navigate(R.id.action_comicFragment_to_detailFragment)
        }
    }

    private fun findHash(string: String): IntArray {
        var hashStart = 0
        var hashEnd = 0

        for (index in 0..string.length) {
            if (string[index] == '#') {
                hashStart = index
                break
            }
        }

        for (index in hashStart until string.length) {
            if (string[index] == ' ') {
                hashEnd = index
                break
            }
            if (index + 1 == string.length) {
                hashEnd = string.length
                break
            }
        }

        return intArrayOf(hashStart, hashEnd)
    }
}