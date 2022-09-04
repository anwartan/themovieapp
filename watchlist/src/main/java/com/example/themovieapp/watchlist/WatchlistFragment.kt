package com.example.themovieapp.watchlist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.themovieapp.R
import com.example.themovieapp.core.ui.MovieListAdapter
import com.example.themovieapp.detail.DetailActivity
import com.example.themovieapp.di.WatchlistModuleDependecies
import com.example.themovieapp.watchlist.databinding.FragmentWatchlistBinding
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class WatchlistFragment : Fragment() {

    private var _binding:FragmentWatchlistBinding? = null
    private val binding get() = _binding!!
    @Inject
    lateinit var factory: ViewModelFactory
    private val watchlistViewModel: WatchlistViewModel by viewModels{
        factory
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerWatchlistComponent.builder().content(context)
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    context,
                    WatchlistModuleDependecies::class.java
                )
            )
            .build()
            .inject(this)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWatchlistBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {

            val movieAdapter = MovieListAdapter()
            movieAdapter.onItemClick = { selectedData ->
                val bundle = bundleOf(DetailActivity.EXTRA_DATA to selectedData.id)
                findNavController().navigate(R.id.action_watchlistFragment_to_detailActivity,bundle)

            }

            watchlistViewModel.watchlistMovies.observe(viewLifecycleOwner){
                val movies = it.map {movieDetail->
                    movieDetail.movie
                }
                movieAdapter.setMovies(movies)
            }

            with(binding.rvMovie) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieAdapter
            }
        }
    }


}