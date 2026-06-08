package org.us.smartbudget.domain.repository

import kotlinx.coroutines.flow.Flow
import org.us.smartbudget.domain.model.Transaction
import org.us.smartbudget.domain.model.TransactionType

interface TransactionRepository {
    suspend fun insertTransaction(title: String, amount: Double, type: TransactionType)
    fun getAllTransactions(): Flow<List<Transaction>>
}