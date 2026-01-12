package com.expensetracker.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.expensetracker.data.models.Person
import com.expensetracker.data.models.Expense

@Dao
interface ExpenseDao {
    @Insert
    suspend fun insertPerson(person: Person): Long
    
    @Query("SELECT * FROM persons ORDER BY name")
    fun getAllPersons(): LiveData<List<Person>>
    
    @Insert
    suspend fun insertExpense(expense: Expense)
    
    @Query("SELECT * FROM expenses ORDER BY timestamp DESC")
    fun getAllExpenses(): LiveData<List<Expense>>
    
    @Query("SELECT * FROM persons WHERE id = :id")
    suspend fun getPersonById(id: Int): Person?
    
    @Query("DELETE FROM expenses WHERE id = :id")
    suspend fun deleteExpense(id: Int)
}
