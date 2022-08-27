package com.example.animalroom

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AnimalRepository(private val animalDao: AnimalDao) {

    val allAnimals = animalDao.getAllAnimals()
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun insertAnimal(newAnimal: Animal) {
        coroutineScope.launch(Dispatchers.IO) {
            animalDao.insertAnimal(newAnimal)
        }
    }

    fun deleteAnimal(name: String) {
        coroutineScope.launch(Dispatchers.IO) {
            animalDao.deleteAnimal(name)
        }
    }



}