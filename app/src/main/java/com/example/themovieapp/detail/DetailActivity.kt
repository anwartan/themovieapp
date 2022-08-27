package com.example.themovieapp.detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.themovieapp.MyApplication
import com.example.themovieapp.R
import com.example.themovieapp.core.data.source.remote.network.ApiConfig
import com.example.themovieapp.core.ui.ViewModelFactory
import com.example.themovieapp.databinding.ActivityDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import javax.inject.Inject
@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra_data"
    }


    private val detailViewModel: DetailViewModel by viewModels()

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbarDetail)

        val idMovie = intent.getIntExtra(EXTRA_DATA, -1)
        detailViewModel.getMovieDetail(idMovie)

        showDetailMovie()

    }

    private fun showDetailMovie() {
        detailViewModel.detailMovie.observe(this, {
            it?.let { movie ->
                supportActionBar?.title = movie.title
                binding.tvDescription.text = movie.overview
                Glide.with(this@DetailActivity)
                    .load(ApiConfig.BASE_IMAGE_URL + movie.posterPath)
                    .error(R.drawable.ic_baseline_image_not_supported_24)
                    .into(binding.ivPoster)

            }
        })

        detailViewModel.isFavoriteMovie.observe(this, {

            val exist = it !== null
            changeFavoriteButton(exist)
            binding.btnFavorite.setOnClickListener {
                detailViewModel.setFavoriteMovie(!exist)
            }

        })

    }

    private fun changeFavoriteButton(favorite: Boolean) {
        if (favorite) {
            binding.btnFavorite.icon = getDrawable(R.drawable.ic_baseline_check_24)
        } else {
            binding.btnFavorite.icon = getDrawable(R.drawable.ic_baseline_add_24)
        }
    }

}