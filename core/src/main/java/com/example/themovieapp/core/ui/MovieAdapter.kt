package com.example.themovieapp.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.themovieapp.core.R
import com.example.themovieapp.core.data.source.remote.network.ApiConfig
import com.example.themovieapp.core.databinding.ItemListMovieBinding
import com.example.themovieapp.core.domain.model.Movie
import com.example.themovieapp.core.utils.CustomDiffUtilCallback

class MovieAdapter:RecyclerView.Adapter<MovieAdapter.ViewHolder>() {


    var onItemClick: ((Movie) -> Unit)? = null
    private val listData = ArrayList<Movie>()

    fun setData(newListData: List<Movie>?) {
        if (newListData == null) return
        val customDiffUtilCallback = CustomDiffUtilCallback(this.listData,newListData)
        val diffResult = DiffUtil.calculateDiff(customDiffUtilCallback)
        listData.clear()
        listData.addAll(newListData)

        diffResult.dispatchUpdatesTo(this)
    }

    inner class ViewHolder(itemView:View): RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListMovieBinding.bind(itemView)
        fun bind(data: Movie) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(ApiConfig.BASE_IMAGE_URL+data.posterPath)
                    .error(R.drawable.ic_baseline_image_not_supported_24)
                    .into(ivPoster)
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_movie,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int {
        return listData.size
    }
}