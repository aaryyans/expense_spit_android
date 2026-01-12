package com.expensetracker.data.models

data class Balance(
    val fromPerson: String,
    val toPerson: String,
    val amount: Double
)
