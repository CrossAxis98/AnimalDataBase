package com.example.animalroom

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [(Animal::class)], version = 1)
abstract class AnimalRoomDatabase: RoomDatabase() {

abstract fun animalDao(): AnimalDao

    companion object {

        private var INSTANCE: AnimalRoomDatabase? = null

        fun getInstance(context: Context): AnimalRoomDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AnimalRoomDatabase::class.java,
                        "animal_database"
                    ).fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }
                return instance
            }
        }

    }

}