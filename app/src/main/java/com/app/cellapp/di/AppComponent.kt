package com.app.cellapp.di

import com.app.cellapp.presentation.CellsViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ExcecutorModule::class, AppModule::class])
interface AppComponent {
    fun inject(viewModel: CellsViewModel)
}