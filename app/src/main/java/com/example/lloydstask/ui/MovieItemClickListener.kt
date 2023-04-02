package com.example.lloydstask.ui

import com.example.lloydstask.data.model.MovieItem

interface MovieItemClickListener {
    fun onClick(movieItem: MovieItem)
}