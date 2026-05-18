package org.us.smartbudget.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape

@Composable
fun Sidebar() {
    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Surface(
                        modifier = Modifier.size(40.dp),
                        shape = RoundedCornerShape(8.dp),
                        color = MaterialTheme.colorScheme.secondaryContainer,
                    ) {
                        IconButton(
                            onClick = { /*TODO*/ },
                            modifier = Modifier.fillMaxSize(),
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Settings,
                                contentDescription = "Ustawienia",
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Text("Menu", style = MaterialTheme.typography.titleMedium)
                }

                HorizontalDivider()
                NavigationDrawerItem(
                    label = { Text(text = "Pulpit") },
                    selected = false,
                    onClick = { /*TODO*/ },
                )
                NavigationDrawerItem(
                    label = { Text(text = "Historia") },
                    selected = false,
                    onClick = { /*TODO*/ },
                )

            }
        },
    ) {

    }
}