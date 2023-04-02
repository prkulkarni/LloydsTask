package com.example.lloydstask.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lloydstask.data.model.MovieItem
import com.example.lloydstask.databinding.ItemMovieListBinding
import com.example.lloydstask.domain.model.MovieDomainItem
import com.example.lloydstask.ui.MovieItemClickListener
import com.example.lloydstask.ui.viewholders.MovieItemViewHolder
import com.example.lloydstask.ui.wrapper.ImageLoader

class MovieItemAdapter(
    private val itemList: List<MovieItem>,
    private val imageLoader: ImageLoader,
    private val listener: MovieItemClickListener
) : RecyclerView.Adapter<MovieItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder {
        val context = parent.context
        val binding = ItemMovieListBinding.inflate(LayoutInflater.from(context), parent, false)
        return MovieItemViewHolder(binding, imageLoader, listener)
    }

    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount() = itemList.size
}