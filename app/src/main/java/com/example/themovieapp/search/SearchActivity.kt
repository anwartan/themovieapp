package com.example.themovieapp.search

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.themovieapp.R
import com.example.themovieapp.core.ui.MovieListAdapter
import com.example.themovieapp.core.ui.ViewModelFactory
import com.example.themovieapp.databinding.ActivitySearchBinding
import com.example.themovieapp.detail.DetailActivity

class SearchActivity : AppCompatActivity() {

    private lateinit var binding:ActivitySearchBinding
    private lateinit var viewModel: SearchViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title=getString(R.string.search_movie)
        val movieListAdapter = MovieListAdapter()

        movieListAdapter.onItemClick = {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, it.id)
            startActivity(intent)
        }
        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[SearchViewModel::class.java]
        binding.tfSearch.doAfterTextChanged {
            it?.let {
                viewModel.searchMovie(it.toString())
            }
        }

        viewModel.movie.observe(this,{
            movieListAdapter.setMovies(it)

        })

        with(binding.rvMovie){
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = movieListAdapter
        }
    }

}