package org.us.smartbudget.data.repository

import kotlinx.coroutines.flow.Flow
import org.us.smartbudget.data.model.Transaction
import org.us.smartbudget.data.model.TransactionType

interface TransactionRepository {
    suspend fun insertTransaction(title: String, amount: Double, type: TransactionType)
    fun getAllTransactions(): Flow<List<Transaction>>
}