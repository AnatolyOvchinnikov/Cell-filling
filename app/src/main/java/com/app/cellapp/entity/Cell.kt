package com.app.cellapp.entity

import com.app.cellapp.App
import com.app.cellapp.R
import java.util.concurrent.atomic.AtomicInteger

data class Cell(val id: Int = CellID.id, val type: Int) {
    val name: String
    val description: String
    init {
        App.applicationContext().apply {
            name = when(type) {
                TYPE_ALIVE -> getString(R.string.cell_alive_name)
                TYPE_DEAD -> getString(R.string.cell_dead_name)
                TYPE_LIFE -> getString(R.string.cell_life_name)
                else -> throw Throwable("Wrong cell type")
            }
            description = when(type) {
                TYPE_ALIVE -> getString(R.string.cell_alive_description)
                TYPE_DEAD -> getString(R.string.cell_dead_description)
                TYPE_LIFE -> getString(R.string.cell_life_description)
                else -> throw Throwable("Wrong cell type")
            }
        }
    }
    companion object {
        const val TYPE_ALIVE = 0
        const val TYPE_DEAD = 1
        const val TYPE_LIFE = 2
    }

    object CellID {
        private val c = AtomicInteger(0)
        val id: Int
            get() = c.incrementAndGet()
    }
}