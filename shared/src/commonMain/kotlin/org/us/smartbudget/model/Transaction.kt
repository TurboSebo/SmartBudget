package org.us.smartbudget.model

data class Transaction(
    val id: Long,
    var title: String? = "transaction title",
    var amount: Double? = null,
    var date: Long? = null,
)
