package com.expensetracker.data.repository

import androidx.lifecycle.LiveData
import com.expensetracker.data.database.ExpenseDao
import com.expensetracker.data.models.Balance
import com.expensetracker.data.models.Expense
import com.expensetracker.data.models.Person

class ExpenseRepository(private val dao: ExpenseDao) {
    
    val allPersons: LiveData<List<Person>> = dao.getAllPersons()
    val allExpenses: LiveData<List<Expense>> = dao.getAllExpenses()
    
    suspend fun addPerson(person: Person) = dao.insertPerson(person)
    
    suspend fun addExpense(expense: Expense) = dao.insertExpense(expense)
    
    suspend fun deleteExpense(id: Int) = dao.deleteExpense(id)
    
    suspend fun calculateBalances(persons: List<Person>, expenses: List<Expense>): List<Balance> {
        val balanceMap = mutableMapOf<Pair<Int, Int>, Double>()
        val totalPersons = persons.size
        
        if (totalPersons == 0) return emptyList()
        
        // Calculate splits
        expenses.forEach { expense ->
            val splitAmount = expense.amount / totalPersons
            persons.forEach { person ->
                if (person.id != expense.paidById) {
                    val key = Pair(person.id, expense.paidById)
                    balanceMap[key] = balanceMap.getOrDefault(key, 0.0) + splitAmount
                }
            }
        }
        
        // Simplify balances
        val netBalance = mutableMapOf<Int, Double>()
        balanceMap.forEach { (pair, amount) ->
            netBalance[pair.first] = netBalance.getOrDefault(pair.first, 0.0) - amount
            netBalance[pair.second] = netBalance.getOrDefault(pair.second, 0.0) + amount
        }
        
        // Create balance list
        val balances = mutableListOf<Balance>()
        val personMap = persons.associateBy { it.id }
        
        netBalance.forEach { (personId, amount) ->
            if (amount < 0) {
                val creditors = netBalance.filter { it.value > 0 }
                creditors.forEach { (creditorId, creditorAmount) ->
                    if (creditorAmount > 0) {
                        val settleAmount = minOf(-amount, creditorAmount)
                        balances.add(
                            Balance(
                                personMap[personId]?.name ?: "",
                                personMap[creditorId]?.name ?: "",
                                settleAmount
                            )
                        )
                    }
                }
            }
        }
        
        return balances.filter { it.amount > 0.01 }
    }
}
