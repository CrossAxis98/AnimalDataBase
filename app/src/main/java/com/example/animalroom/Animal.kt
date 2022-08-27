package com.example.animalroom

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "animals")
data class Animal(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "animalId")
    val id: Int = 0,
    val aniamlName: String,
    val animalCategory: String
)