package org.us.smartbudget.di

import org.us.smartbudget.domain.repository.TransactionRepository
import org.us.smartbudget.domain.repository.TransactionRepositoryImpl
import org.us.smartbudget.database.SmartBudgetDatabase
import org.us.smartbudget.getPlatform

object ServiceLocator {
    private var database: SmartBudgetDatabase? = null
    private var transactionRepository: TransactionRepository? = null

    fun initializeDatabase(db: SmartBudgetDatabase) {
        database = db
        // Czyszczenie cache'u repozytorium jeśli baza się zmienia
        transactionRepository = null
    }

    fun getTransactionRepository(): TransactionRepository {
        return transactionRepository ?: run {
            val db = database ?: throw IllegalStateException(
                "Database not initialized. Call initializeDatabase() first."
            )
            TransactionRepositoryImpl(db).also { transactionRepository = it }
        }
    }

    fun getDatabase(): SmartBudgetDatabase {
        return database ?: throw IllegalStateException(
            "Database not initialized. Call initializeDatabase() first."
        )
    }
}

