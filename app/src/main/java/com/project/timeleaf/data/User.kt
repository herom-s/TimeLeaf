package com.project.timeleaf.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "user_id") val user_id : Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "gender") val gender: String,
    @ColumnInfo(name = "age") val age: Int,
    @ColumnInfo(name = "lvl_physical_activity") val lvl_physical_activity: Double,
    @ColumnInfo(name = "weight") val weight: Double,
    @ColumnInfo(name = "height") val height: Double,
    @ColumnInfo(name = "bmi") val bmi: Double,
    @ColumnInfo(name = "bmr") val bmr: Double,
    @ColumnInfo(name = "tev") val tev: Double
)