package com.example.themovieapp.search

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.themovieapp.MyApplication
import com.example.themovieapp.R
import com.example.themovieapp.core.ui.MovieListAdapter
import com.example.themovieapp.core.ui.ViewModelFactory
import com.example.themovieapp.databinding.ActivitySearchBinding
import com.example.themovieapp.detail.DetailActivity
import javax.inject.Inject

class SearchActivity : AppCompatActivity() {


    @Inject
    lateinit var factory: ViewModelFactory

    private val searchViewModel: SearchViewModel by viewModels {
        factory
    }

    private lateinit var binding: ActivitySearchBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = getString(R.string.search_movie)
        val movieListAdapter = MovieListAdapter()

        movieListAdapter.onItemClick = {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, it.id)
            startActivity(intent)
        }

        binding.tfSearch.doAfterTextChanged {
            it?.let {
                searchViewModel.searchMovie(it.toString())
            }
        }

        searchViewModel.movie.observe(this, {
            movieListAdapter.setMovies(it)

        })

        with(binding.rvMovie) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = movieListAdapter
        }
    }

}