package com.app.cellapp.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.cellapp.databinding.CellLayoutItemBinding
import com.app.cellapp.entity.Cell
import com.app.cellapp.ui.BindableAdapter

class CellsAdapter : RecyclerView.Adapter<CellsAdapter.ViewHolder>(),
    BindableAdapter<ArrayList<Cell>> {

    private val data = ArrayList<Cell>()

    override fun setData(data: ArrayList<Cell>) {
        val diffUtilCallback = DiffCallback(this.data, data)
        val diffResult = DiffUtil.calculateDiff(diffUtilCallback)

        this.data.clear()
        this.data.addAll(data)

        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            com.app.cellapp.R.layout.cell_layout_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(this.data[position])

    override fun getItemCount(): Int = this.data.size

    inner class ViewHolder(val binding: CellLayoutItemBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.executePendingBindings()
        }

        fun bind(cell: Cell) {
            binding.cell = cell
        }
    }

    inner class DiffCallback(val oldItems: List<Cell>, val newItems: List<Cell>) : DiffUtil.Callback() {
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean
                = oldItems[ oldItemPosition ].id == newItems[ newItemPosition ].id

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean
                = oldItems[ oldItemPosition ] == newItems[ newItemPosition ]

        override fun getOldListSize(): Int = oldItems.size

        override fun getNewListSize(): Int = newItems.size
    }
}