package com.project.timeleaf.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "userId") val userId : Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "gender") val gender: String,
    @ColumnInfo(name = "age") val age: Int,
    @ColumnInfo(name = "lvlPhysicalActivity") val lvlPhysicalActivity: Double,
    @ColumnInfo(name = "weight") val weight: Double,
    @ColumnInfo(name = "height") val height: Double,
    @ColumnInfo(name = "bmi") val bmi: Double,
    @ColumnInfo(name = "bmr") val bmr: Double,
    @ColumnInfo(name = "tev") val tev: Double
)