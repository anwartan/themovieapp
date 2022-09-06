package com.example.themovieapp.detail

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import com.bumptech.glide.Glide
import com.example.themovieapp.R
import com.example.themovieapp.core.data.source.remote.network.ApiConfig
import com.example.themovieapp.databinding.ActivityDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {


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
        detailViewModel.detailMovie.observe(this) {
            it?.let { movieDetail ->
                binding.toolbarDetail.title = movieDetail.movie.title
                binding.tvDescription.text = movieDetail.movie.overview
                Glide.with(this@DetailActivity)
                    .load(ApiConfig.BASE_IMAGE_URL + movieDetail.movie.posterPath)
                    .error(R.drawable.ic_baseline_image_not_supported_24)
                    .into(binding.ivPoster)
                changeFavoriteButton(movieDetail.detail.isFavorite)
                changeWatchButton(movieDetail.detail.isWatch)

                binding.btnFavorite.setOnClickListener {
                    detailViewModel.setFavoriteMovie(!movieDetail.detail.isFavorite)
                }
                binding.btnShare.icon = AppCompatResources.getDrawable(this,R.drawable.ic_baseline_reply_24)
                binding.btnShare.setOnClickListener {
                    val sendIntent: Intent = Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_TEXT, getString(R.string.share_description, movieDetail.movie.originalTitle,ApiConfig.BASE_IMAGE_URL+movieDetail.movie.posterPath))
                        type = "text/plain"
                    }

                    val shareIntent = Intent.createChooser(sendIntent, null)
                    startActivity(shareIntent)
                }
                binding.btnWatch.setOnClickListener {
                    detailViewModel.setWatchMovie(!movieDetail.detail.isWatch)
                }

            }
        }



    }

    private fun changeFavoriteButton(favorite: Boolean) {
        if (favorite) {
            binding.btnFavorite.icon = AppCompatResources.getDrawable(this,R.drawable.ic_baseline_check_24)
        } else {
            binding.btnFavorite.icon = AppCompatResources.getDrawable(this,R.drawable.ic_baseline_add_24)
        }
    }
    private fun changeWatchButton(favorite: Boolean) {
        if (favorite) {
            binding.btnWatch.icon = AppCompatResources.getDrawable(this,R.drawable.ic_baseline_check_24)
        } else {
            binding.btnWatch.icon = AppCompatResources.getDrawable(this,R.drawable.ic_baseline_add_24)
        }
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }


}