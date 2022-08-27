package com.example.animalroom

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class MainViewModel(application: Application): ViewModel() {

    val allAnimals: LiveData<List<Animal>>
    private val repository: AnimalRepository

    init {
        val animalDb = AnimalRoomDatabase.getInstance(application)
        val animalDao = animalDb.animalDao()
        repository = AnimalRepository(animalDao)

        allAnimals = repository.allAnimals
    }

    fun insertAnimal(animal: Animal) {
        repository.insertAnimal(animal)
    }

    fun deleteAnimal(name: String) {
        repository.deleteAnimal(name)
    }
}