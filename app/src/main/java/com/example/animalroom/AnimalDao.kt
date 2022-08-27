package com.example.animalroom

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AnimalDao {

    @Insert
    fun insertAnimal(animal: Animal)

    @Query("SELECT * FROM animals")
    fun getAllAnimals(): LiveData<List<Animal>>

    @Query("DELETE FROM animals where aniamlName = :name")
    fun deleteAnimal(name: String)

}