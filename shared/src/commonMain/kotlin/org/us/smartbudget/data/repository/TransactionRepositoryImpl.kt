// Ścieżka: org/us/smartbudget/data/repository/TransactionRepositoryImpl.kt
package org.us.smartbudget.data.repository

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import org.us.smartbudget.data.model.Transaction
import org.us.smartbudget.data.model.TransactionType
import org.us.smartbudget.database.SmartBudgetDatabase // Wygenerowana klasa SQLDelight

class TransactionRepositoryImpl(
    private val database: SmartBudgetDatabase
) : TransactionRepository {

    private val queries = database.databaseQueries

    override suspend fun insertTransaction(title: String, amount: Double, type: TransactionType) {
        queries.insertTransaction(
            title = title,
            amount = amount,
            type = type.name, // Zapisujemy enum jako String w bazie
            category = null,
            dateEpoch = null
        )
    }

    override fun getAllTransactions(): Flow<List<Transaction>> {
        return queries.selectAllTransactions(::mapToDomainModel)
            .asFlow()
            .mapToList(Dispatchers.Default)
    }

    // Funkcja mapująca model bazodanowy (z SQLDelight) na model domenowy (Twój data class)
    private fun mapToDomainModel(
        id: Long,
        title: String?,
        amount: Double?,
        type: String,
        category: String?,
        dateEpoch: Long?
    ): Transaction {
        return Transaction(
            id = id,
            title = title,
            amount = amount,
            type = TransactionType.valueOf(type),
            category = category,
            dateEpoch = dateEpoch
        )
    }
}