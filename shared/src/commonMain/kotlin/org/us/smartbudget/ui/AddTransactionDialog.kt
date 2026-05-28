package org.us.smartbudget.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import org.us.smartbudget.model.TransactionType

@Composable
fun AddTransactionDialog(
    onDismiss: () -> Unit,
    onSave: (title: String, amount: Double, type: TransactionType) -> Unit
){
    var title by remember { mutableStateOf("") } //stany pól co użytkownik wpisuje
    var amountText by remember { mutableStateOf("") }

    var selectedType by remember { mutableStateOf(TransactionType.EXPENSE) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Nowa transakcja") },
        text = {
            Column(modifier = Modifier.fillMaxWidth()) {
                // Pole na opis
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Opis (np. Zakupy)") },
                    modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
                )

                OutlinedTextField(
                    value = amountText,
                    onValueChange = { amountText = it },
                    label = { Text("Kwota") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
                )
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                    FilterChip(
                        selected = selectedType == TransactionType.INCOME,
                        onClick = { selectedType = TransactionType.INCOME },
                        label = { Text("Przychód") }
                    )
                    FilterChip(
                        selected = selectedType == TransactionType.EXPENSE,
                        onClick = { selectedType = TransactionType.EXPENSE },
                        label = { Text("Wydatek") }
                    )
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    val amount = amountText.toDoubleOrNull() ?: 0.0
                    onSave(title, amount, selectedType)
                }
            ){
                Text("Zapisz")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss){
                Text("Anuluj")
            }
        }
    )
}