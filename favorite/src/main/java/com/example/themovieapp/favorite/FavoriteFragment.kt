package com.example.themovieapp.favorite

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.themovieapp.MyApplication
import com.example.themovieapp.R
import com.example.themovieapp.core.ui.MovieListAdapter
import com.example.themovieapp.detail.DetailActivity
import com.example.themovieapp.di.FavoriteModuleDependecies
import com.example.themovieapp.favorite.databinding.FragmentFavoriteBinding
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class FavoriteFragment : Fragment() {

    @Inject
    lateinit var factory: ViewModelFactory
    private val favoriteViewModel: FavoriteViewModel by viewModels{
        factory
    }

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerFavoriteComponent.builder().context(context)
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    context,
                    FavoriteModuleDependecies::class.java
                )
            )
            .build()
            .inject(this)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {

            val movieAdapter = MovieListAdapter()
            movieAdapter.onItemClick = { selectedData ->
                val bundle = bundleOf(DetailActivity.EXTRA_DATA to selectedData.id)
                findNavController().navigate(R.id.action_favoriteFragment_to_detailActivity,bundle)

            }

            favoriteViewModel.favoriteMovie.observe(viewLifecycleOwner, { movieFavorite ->
                if (movieFavorite != null) {
                    val movies = movieFavorite.map { it.movie }
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