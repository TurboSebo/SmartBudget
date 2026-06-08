// Ścieżka: org/us/smartbudget/ui/SmartBudgetViewModel.kt
package org.us.smartbudget.ui

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.us.smartbudget.di.ServiceLocator
import org.us.smartbudget.data.model.Transaction
import org.us.smartbudget.data.model.TransactionType

class SmartBudgetViewModel {

    private val repository = ServiceLocator.getTransactionRepository()
    // Use Default dispatcher in common module to avoid missing Main dispatcher on some platforms
    private val viewModelScope = CoroutineScope(Dispatchers.Default + SupervisorJob())

    val transactions = repository.getAllTransactions()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = emptyList<Transaction>()
        )

    val totalBalance = transactions.map { list ->
        list.sumOf { transaction ->
            val amount = transaction.amount ?: 0.0
            if (transaction.type == TransactionType.INCOME) amount else -amount
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = 0.0
    )
    fun addTransaction(title: String, amount: Double, type: TransactionType) {
        viewModelScope.launch {
            repository.insertTransaction(title, amount, type)
        }
    }
}