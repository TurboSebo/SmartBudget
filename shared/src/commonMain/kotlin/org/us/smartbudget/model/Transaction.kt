package org.us.smartbudget.model

enum class TransactionType {
    INCOME,
    EXPENSE,
}

data class Transaction(
    val id: Long,
    val title: String? = "transaction title",
    val amount: Double? = null,
    val type: TransactionType,
    val category: String? = null,
    val dateEpoch: Long? = null,

)
