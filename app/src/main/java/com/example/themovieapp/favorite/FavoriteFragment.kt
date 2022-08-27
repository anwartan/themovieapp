package com.example.themovieapp.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.themovieapp.core.ui.MovieListAdapter
import com.example.themovieapp.core.ui.ViewModelFactory
import com.example.themovieapp.databinding.FragmentFavoriteBinding
import com.example.themovieapp.detail.DetailActivity

class FavoriteFragment : Fragment() {

    private var _binding : FragmentFavoriteBinding?=null
    private val binding get() = _binding!!
    private lateinit var favoriteViewModel: FavoriteViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {

            val movieAdapter = MovieListAdapter()
            movieAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA, selectedData.id)
                startActivity(intent)
            }
            val factory = ViewModelFactory.getInstance(requireActivity())
            favoriteViewModel = ViewModelProvider(this, factory)[FavoriteViewModel::class.java]

            favoriteViewModel.favoriteMovie.observe(viewLifecycleOwner, { movieFavorite ->
                if (movieFavorite != null) {
                    val movies =  movieFavorite.map { it.movie }
                    movieAdapter.setMovies(movies)
                }
            })

            with(binding.rvMovie) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieAdapter
            }
        }

    }
}