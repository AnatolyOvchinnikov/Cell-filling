package com.app.cellapp.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.cellapp.App
import com.app.cellapp.entity.Cell
import com.app.cellapp.interactor.CellsInteractor
import javax.inject.Inject

class CellsViewModel : ViewModel() {
    val liveData = MutableLiveData<ArrayList<Cell>>()

    @Inject
    lateinit var interactor: CellsInteractor

    init {
        App.component?.inject(this)
    }

    fun generateCell() {
        interactor.generateCell {
            liveData.postValue(it)
        }
    }
}