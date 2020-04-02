package com.app.cellapp.interactor

import com.app.cellapp.entity.Cell
import java.util.concurrent.Executor
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.random.Random

@Singleton
class CellsInteractor @Inject constructor(
    var ioExecutor: Executor
) {
    private val cellsArray = ArrayList<Cell>()

    /**
     * Generate new cell
     */
    fun generateCell(list:(ArrayList<Cell>) -> (Unit)) {
        ioExecutor.execute {
            val cellType: Int = Random.nextInt(0, 2)
            cellsArray.add(Cell(type = cellType))
            analyzeList()
            list(cellsArray)
        }
    }

    /**
     * Analyze list for new cell
     */
    private fun analyzeList() {
        // Find last 3 alive cells
        if(cellsArray.takeLast(3).filter {
                it.type == Cell.TYPE_ALIVE
            }.size == 3) {
            // Add life cell
            cellsArray.add(Cell(type = Cell.TYPE_LIFE))
            // ... or find 3 dead cells and remove life cell, if it present
        } else if(cellsArray.takeLast(3).filter { it.type == Cell.TYPE_DEAD }.size == 3 && cellsArray.any {
                it.type == Cell.TYPE_LIFE
            }) {
            cellsArray.remove(cellsArray.last {
                it.type == Cell.TYPE_LIFE
            })
        }
    }
}