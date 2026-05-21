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
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Swipe
import androidx.compose.runtime.rememberCoroutineScope

@Composable
fun Sidebar(
    drawerState: DrawerState,
    onCloseClick: () -> Unit,
    content: @Composable () -> Unit //przyjmowanie zawartości ekranu aby móc wyjeżdzać na nią z boku
) {

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Spacer(modifier = Modifier.width(12.dp))
                //Text("Menu", style = MaterialTheme.typography.titleMedium)

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround,
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
                    Surface(
                        modifier = Modifier.size(40.dp),
                        shape = RoundedCornerShape(8.dp),
                        color = MaterialTheme.colorScheme.secondaryContainer,
                    ){
                        IconButton(
                            onClick = { onCloseClick() },
                            modifier = Modifier.fillMaxSize(),
                        ){
                            Icon(
                                imageVector = Icons.Filled.Close,
                                contentDescription = "Zamknij",
                            )
                        }
                    }


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
        content()

    }
}