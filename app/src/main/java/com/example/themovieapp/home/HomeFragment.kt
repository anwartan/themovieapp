package com.example.themovieapp.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.themovieapp.MyApplication
import com.example.themovieapp.core.data.Resource
import com.example.themovieapp.core.domain.model.Movie
import com.example.themovieapp.core.ui.MovieAdapter
import com.example.themovieapp.core.ui.ViewModelFactory
import com.example.themovieapp.databinding.FragmentHomeBinding
import com.example.themovieapp.detail.DetailActivity
import javax.inject.Inject


class HomeFragment : Fragment() {


    @Inject
    lateinit var factory: ViewModelFactory

    private val homeViewModel: HomeViewModel by viewModels {
        factory
    }


    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as MyApplication).appComponent.inject(this)
    }

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
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA, selectedData.id)
                startActivity(intent)
            }
            movieAdapter.onItemClick = movieItemClick
            popularAdapter.onItemClick = movieItemClick
            topRatedAdapter.onItemClick = movieItemClick
//            val factory = ViewModelFactory.getInstance(requireActivity())
//            homeViewModel = ViewModelProvider(this,factory)[HomeViewModel::class.java]


            homeViewModel.movie.observe(viewLifecycleOwner,{movie->
                if (movie != null) {
                    when (movie) {
                        is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            movieAdapter.setData(movie.data)
                        }
                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                        }
                    }
                }
            })

            homeViewModel.popularMovie.observe(viewLifecycleOwner,{movie->
                when (movie) {
                    is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        popularAdapter.setData(movie.data)
                    }
                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                    }
                }
            })

            homeViewModel.topRatedMovie.observe(viewLifecycleOwner,{movie->
                when (movie) {
                    is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        topRatedAdapter.setData(movie.data)
                    }
                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                    }
                }
            })


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