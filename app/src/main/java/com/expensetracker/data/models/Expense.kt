package com.expensetracker.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "expenses")
data class Expense(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val paidById: Int,
    val description: String,
    val amount: Double,
    val timestamp: Long = System.currentTimeMillis()
)
