package com.example.lloydstask.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.example.lloydstask.R
import com.example.lloydstask.data.model.MovieDetailsModel
import com.example.lloydstask.databinding.FragmentMovieDetailsBinding
import com.example.lloydstask.domain.model.MovieDetailsDomainModel
import com.example.lloydstask.ui.wrapper.ImageLoader
import com.example.lloydstask.utils.Result
import com.example.lloydstask.viewmodel.MovieDetailsViewModel
import com.example.lloydstask.viewmodel.MovieListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MovieDetailsFragment : BaseFragment() {

    @Inject
    lateinit var imageLoader: ImageLoader

    private lateinit var binding: FragmentMovieDetailsBinding
    private val viewModel by viewModels<MovieDetailsViewModel>()
    private val args: MovieDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieDetailsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movieId = args.movieId
        viewModel.fetchMovieDetails(movieId)
    }

    override fun setupObserver() {
        lifecycleScope.launch(Dispatchers.Main) {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.movieDetailsState.collect {
                    handleState(it)
                }
            }
        }
    }

    private fun handleState(result: Result<MovieDetailsModel?>) {
        when (result) {
            is Result.Loading -> {
                showProgressBar(binding.progressBar)
            }
            is Result.Success -> {
                hideProgressBar(binding.progressBar)
                handleSuccess(result.data)
            }
            is Result.Error -> {
                hideProgressBar(binding.progressBar)
                Toast.makeText(activity, result.message, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun handleSuccess(movieDetails: MovieDetailsModel?) {
        movieDetails?.let {
            imageLoader.loadImage(
                it.imageUrl,
                R.drawable.circular_progress_bar,
                binding.movieImage
            )
            binding.movieTitleText.text = it.fullTitle
            binding.plotText.text = it.plot
        }
    }
}