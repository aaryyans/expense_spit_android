package com.expensetracker.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.expensetracker.data.models.Expense
import com.expensetracker.databinding.ItemExpenseBinding
import java.text.SimpleDateFormat
import java.util.*

class ExpenseAdapter(
    private val onDelete: (Expense) -> Unit
) : ListAdapter<Expense, ExpenseAdapter.ExpenseViewHolder>(ExpenseDiffCallback()) {
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder {
        val binding = ItemExpenseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ExpenseViewHolder(binding)
    }
    
    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    
    inner class ExpenseViewHolder(private val binding: ItemExpenseBinding) : 
        RecyclerView.ViewHolder(binding.root) {
        
        fun bind(expense: Expense) {
            binding.tvDescription.text = expense.description
            binding.tvAmount.text = "$${String.format("%.2f", expense.amount)}"
            binding.tvDate.text = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
                .format(Date(expense.timestamp))
            
            binding.root.setOnLongClickListener {
                onDelete(expense)
                true
            }
        }
    }
    
    class ExpenseDiffCallback : DiffUtil.ItemCallback<Expense>() {
        override fun areItemsTheSame(oldItem: Expense, newItem: Expense) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Expense, newItem: Expense) = oldItem == newItem
    }
}
