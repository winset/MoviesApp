package ru.padawans.moviesapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ru.padawans.moviesapp.BuildConfig
import ru.padawans.moviesapp.R
import ru.padawans.moviesapp.data.model.upcoming.Results

class NowPlayingMoviesAdapter: RecyclerView.Adapter<NowPlayingMoviesAdapter.NowPlayingMoviesViewHolder>() {

    private var results:MutableList<Results> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NowPlayingMoviesViewHolder {
        val inflater= LayoutInflater.from(parent.context)
        return NowPlayingMoviesViewHolder(inflater,parent)
    }

    override fun onBindViewHolder(holder: NowPlayingMoviesViewHolder, position: Int) {
        holder.bind(results[position])
    }

    override fun getItemCount(): Int {
        return results.size
    }

    fun addData(results: List<Results>?){
        this.results.clear()
        if (results != null) {
            this.results = results.toMutableList()
        }
        notifyDataSetChanged()
    }

    class  NowPlayingMoviesViewHolder(inflater: LayoutInflater, parent: ViewGroup): RecyclerView.ViewHolder(inflater.inflate(
        R.layout.now_playing_movie_item,parent,false)){

        private val text: TextView = itemView.findViewById(R.id.now_playing_recycler_tv)
        private val image: ImageView = itemView.findViewById(R.id.now_playing_recycler_iv)

        fun bind(result: Results){
            text.text = result.title
            // w300 размер изображения https://developers.themoviedb.org/3/getting-started/images
            val imageUrl:String = BuildConfig.BASE_IMG_URL + "w300"+ result.posterPath
            Picasso.get()
                .load(imageUrl)
                .into(image)
        }
    }
}