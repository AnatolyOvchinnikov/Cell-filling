package com.app.cellapp.ui

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.cellapp.R
import com.app.cellapp.entity.Cell

@BindingAdapter("bind:data")
fun <T> setData(recyclerView: RecyclerView, data: T) {
    if (recyclerView.adapter is BindableAdapter<*> && data != null && data is List<*>) {
        (recyclerView.adapter as BindableAdapter<T>).setData(data)
        recyclerView.smoothScrollToPosition(data.size - 1)
    }
}

@BindingAdapter("bind:image")
fun ImageView.setImage(type: Int) {
    this.setBackgroundResource(when(type) {
        Cell.TYPE_ALIVE -> R.drawable.bg_cell_alive
        Cell.TYPE_DEAD -> R.drawable.bg_cell_dead
        Cell.TYPE_LIFE -> R.drawable.bg_cell_life
        else -> throw Throwable("Wrong cell type")
    })
    this.setImageResource(when(type) {
        Cell.TYPE_ALIVE -> R.drawable.ic_cell_alive
        Cell.TYPE_DEAD -> R.drawable.ic_cell_dead
        Cell.TYPE_LIFE -> R.drawable.ic_cell_life
        else -> throw Throwable("Wrong cell type")
    })
}