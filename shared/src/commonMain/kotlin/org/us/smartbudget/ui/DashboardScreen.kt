package org.us.smartbudget.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.us.smartbudget.data.model.TransactionType

@Composable
fun DashboardScreen(viewModel: SmartBudgetViewModel) {
    // Nasłuchiwanie zmian ze strumieni (Flow) z ViewModelu
    val transactions by viewModel.transactions.collectAsState(initial = emptyList())
    val balance by viewModel.totalBalance.collectAsState(initial = 0.0)

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Aktualne saldo",
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = "$balance PLN",
            style = MaterialTheme.typography.headlineLarge,
            color = if (balance >= 0) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error
        )

        Spacer(modifier = Modifier.height(24.dp))

        HorizontalDivider(modifier = Modifier.fillMaxWidth())

        // Przewijana lista transakcji
        LazyColumn(
            modifier = Modifier.fillMaxSize().weight(1f),
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            items(transactions) { transaction ->
                ListItem(
                    headlineContent = { Text(transaction.title ?: "Brak opisu") },
                    supportingContent = { Text(transaction.type.name) },
                    trailingContent = {
                        Text(
                            text = "${transaction.amount} PLN",
                            color = if (transaction.type == TransactionType.INCOME)
                                MaterialTheme.colorScheme.primary
                            else
                                MaterialTheme.colorScheme.error
                        )
                    }
                )
            }
        }
    }
}