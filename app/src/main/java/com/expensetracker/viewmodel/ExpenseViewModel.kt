package com.expensetracker.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.expensetracker.data.database.ExpenseDatabase
import com.expensetracker.data.models.Expense
import com.expensetracker.data.models.Person
import com.expensetracker.data.repository.ExpenseRepository
import kotlinx.coroutines.launch

class ExpenseViewModel(application: Application) : AndroidViewModel(application) {
    
    private val repository: ExpenseRepository
    val allPersons: LiveData<List<Person>>
    val allExpenses: LiveData<List<Expense>>
    
    init {
        val dao = ExpenseDatabase.getDatabase(application).expenseDao()
        repository = ExpenseRepository(dao)
        allPersons = repository.allPersons
        allExpenses = repository.allExpenses
    }
    
    fun addPerson(name: String) = viewModelScope.launch {
        repository.addPerson(Person(name = name))
    }
    
    fun addExpense(paidById: Int, description: String, amount: Double) = viewModelScope.launch {
        repository.addExpense(Expense(paidById = paidById, description = description, amount = amount))
    }
    
    fun deleteExpense(id: Int) = viewModelScope.launch {
        repository.deleteExpense(id)
    }
    
    suspend fun getBalances(persons: List<Person>, expenses: List<Expense>) = 
        repository.calculateBalances(persons, expenses)
}
