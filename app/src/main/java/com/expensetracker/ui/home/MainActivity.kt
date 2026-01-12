package com.expensetracker.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.expensetracker.databinding.ActivityMainBinding
import com.expensetracker.databinding.DialogAddExpenseBinding
import com.expensetracker.databinding.DialogAddPersonBinding
import com.expensetracker.viewmodel.ExpenseViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: ExpenseViewModel
    private lateinit var expenseAdapter: ExpenseAdapter
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        viewModel = ViewModelProvider(this)[ExpenseViewModel::class.java]
        
        setupRecyclerView()
        observeData()
        setupButtons()
    }
    
    private fun setupRecyclerView() {
        expenseAdapter = ExpenseAdapter { expense ->
            viewModel.deleteExpense(expense.id)
        }
        binding.recyclerExpenses.apply {
            adapter = expenseAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }
    
    private fun observeData() {
        viewModel.allExpenses.observe(this) { expenses ->
            expenseAdapter.submitList(expenses)
        }
    }
    
    private fun setupButtons() {
        binding.fabAddExpense.setOnClickListener {
            showAddExpenseDialog()
        }
        
        binding.btnAddPerson.setOnClickListener {
            showAddPersonDialog()
        }
        
        binding.btnViewBalances.setOnClickListener {
            showBalances()
        }
    }
    
    private fun showAddExpenseDialog() {
        val dialogBinding = DialogAddExpenseBinding.inflate(LayoutInflater.from(this))
        
        viewModel.allPersons.observe(this) { persons ->
            if (persons.isEmpty()) {
                Toast.makeText(this, "Add people first", Toast.LENGTH_SHORT).show()
                return@observe
            }
            
            val personNames = persons.map { it.name }
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, personNames)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            dialogBinding.spinnerPaidBy.adapter = adapter
            
            AlertDialog.Builder(this)
                .setTitle("Add Expense")
                .setView(dialogBinding.root)
                .setPositiveButton("Add") { _, _ ->
                    val description = dialogBinding.etDescription.text.toString()
                    val amount = dialogBinding.etAmount.text.toString().toDoubleOrNull()
                    val selectedPerson = persons[dialogBinding.spinnerPaidBy.selectedItemPosition]
                    
                    if (description.isNotEmpty() && amount != null && amount > 0) {
                        viewModel.addExpense(selectedPerson.id, description, amount)
                    } else {
                        Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show()
                    }
                }
                .setNegativeButton("Cancel", null)
                .show()
        }
    }
    
    private fun showAddPersonDialog() {
        val dialogBinding = DialogAddPersonBinding.inflate(LayoutInflater.from(this))
        
        AlertDialog.Builder(this)
            .setTitle("Add Person")
            .setView(dialogBinding.root)
            .setPositiveButton("Add") { _, _ ->
                val name = dialogBinding.etPersonName.text.toString()
                if (name.isNotEmpty()) {
                    viewModel.addPerson(name)
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
    
    private fun showBalances() {
        lifecycleScope.launch {
            val persons = viewModel.allPersons.value ?: emptyList()
            val expenses = viewModel.allExpenses.value ?: emptyList()
            val balances = viewModel.getBalances(persons, expenses)
            
            val message = if (balances.isEmpty()) {
                "All settled up!"
            } else {
                balances.joinToString("\n") { 
                    "${it.fromPerson} owes ${it.toPerson}: $${String.format("%.2f", it.amount)}"
                }
            }
            
            AlertDialog.Builder(this@MainActivity)
                .setTitle("Balances")
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show()
        }
    }
}
