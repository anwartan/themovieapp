package com.example.themovieapp.search

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.themovieapp.core.ui.MovieListAdapter
import com.example.themovieapp.detail.DetailActivity
import com.example.themovieapp.di.SearchModuleDependecies
import com.example.themovieapp.search.databinding.ActivitySearchBinding
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject


class SearchActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelFactory

    private val searchViewModel: SearchViewModel by viewModels {
        factory
    }

    private lateinit var binding: ActivitySearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerSearchComponent.builder()
            .context(this)
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    applicationContext,
                    SearchModuleDependecies::class.java
                )
            )
            .build()
            .inject(this)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = getString(R.string.search_title)
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