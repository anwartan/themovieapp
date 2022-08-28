package com.example.themovieapp.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.themovieapp.core.R
import com.example.themovieapp.core.data.source.remote.network.ApiConfig
import com.example.themovieapp.core.databinding.ItemListMovieVerticalBinding
import com.example.themovieapp.core.domain.model.Movie

class MovieListAdapter:RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    var onItemClick : ((Movie)->Unit)?=null
    private var movies = ArrayList<Movie>()
    fun setMovies(value:List<Movie>?) {
            if(value==null) return
            movies.clear()
            movies.addAll(value)
            notifyDataSetChanged()
        }



    inner class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        private var binding = ItemListMovieVerticalBinding.bind(itemView)
        fun bind(movie: Movie) {
            with(binding) {
                tvDescription.text = movie.title
                tvTitle.text = movie.originalTitle
                Glide.with(itemView)
                    .load(ApiConfig.BASE_IMAGE_URL+movie.posterPath)
                    .error(R.drawable.ic_baseline_image_not_supported_24)
                    .into(ivPoster)
            }

        }
        init {

            binding.root.setOnClickListener {
                onItemClick?.invoke(movies[adapterPosition])
            }


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_movie_vertical,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = movies[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}