package com.example.lloydstask.ui.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.example.lloydstask.R
import com.example.lloydstask.data.model.MovieItem
import com.example.lloydstask.databinding.ItemMovieListBinding
import com.example.lloydstask.domain.model.MovieDomainItem
import com.example.lloydstask.ui.MovieItemClickListener
import com.example.lloydstask.ui.wrapper.ImageLoader

class MovieItemViewHolder(
    private val binding: ItemMovieListBinding,
    private val imageLoader: ImageLoader,
    private val listener: MovieItemClickListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(movieDomainItem: MovieItem) {
        binding.titleText.text = movieDomainItem.title
        imageLoader.loadImage(
            movieDomainItem.imageUrl,
            R.drawable.circular_progress_bar,
            binding.thumbnailImage
        )

        binding.root.setOnClickListener {
            listener.onClick(movieDomainItem)
        }
    }
}