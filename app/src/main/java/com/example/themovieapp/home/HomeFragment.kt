package com.example.themovieapp.home

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
import com.example.themovieapp.core.data.Resource
import com.example.themovieapp.core.domain.model.Movie
import com.example.themovieapp.core.ui.MovieAdapter
import com.example.themovieapp.databinding.FragmentHomeBinding
import com.example.themovieapp.detail.DetailActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels ()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(activity!=null){

            val movieAdapter = MovieAdapter()
            val popularAdapter = MovieAdapter()
            val topRatedAdapter = MovieAdapter()
            val movieItemClick ={ selectedData:Movie ->
                val bundle = bundleOf(DetailActivity.EXTRA_DATA to selectedData.id)
                findNavController().navigate(R.id.action_homeFragment_to_detailActivity,bundle)

            }
            movieAdapter.onItemClick = movieItemClick
            popularAdapter.onItemClick = movieItemClick
            topRatedAdapter.onItemClick = movieItemClick

            homeViewModel.movie.observe(viewLifecycleOwner){movie->
                if (movie != null) {
                    when (movie) {
                        is Resource.Loading -> binding.progressBarNowPlaying.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding.progressBarNowPlaying.visibility = View.GONE
                            movieAdapter.setData(movie.data)
                        }
                        is Resource.Error -> {
                            binding.progressBarNowPlaying.visibility = View.GONE
                        }
                    }
                }
            }

            homeViewModel.popularMovie.observe(viewLifecycleOwner){movie->
                when (movie) {
                    is Resource.Loading -> binding.progressBarPopular.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.progressBarPopular.visibility = View.GONE
                        popularAdapter.setData(movie.data)
                    }
                    is Resource.Error -> {
                        binding.progressBarPopular.visibility = View.GONE
                    }
                }
            }

            homeViewModel.topRatedMovie.observe(viewLifecycleOwner){movie->
                when (movie) {
                    is Resource.Loading -> binding.progressBarTopRated.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.progressBarTopRated.visibility = View.GONE
                        topRatedAdapter.setData(movie.data)
                    }
                    is Resource.Error -> {
                        binding.progressBarTopRated.visibility = View.GONE
                    }
                }
            }


            with(binding.rvMovie) {
                layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)

                setHasFixedSize(true)
                adapter = movieAdapter
            }
            with(binding.rvPopular) {
                layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)

                setHasFixedSize(true)
                adapter = popularAdapter
            }
            with(binding.rvTopRated) {
                layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)

                setHasFixedSize(true)
                adapter = topRatedAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding =null
    }

}