package org.us.smartbudget

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.launch
import org.us.smartbudget.ui.DashboardScreen
import org.us.smartbudget.ui.Sidebar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun App() {
    MaterialTheme {
        val drawerState = rememberDrawerState(DrawerValue.Closed)
        val scope = rememberCoroutineScope()

        Sidebar(
            drawerState = drawerState,
            onCloseClick = { scope.launch { drawerState.close() } },
        ) {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text("SmartBudget") },
                        navigationIcon = { // hamburger menu
                            IconButton(onClick = { scope.launch { drawerState.open() } }) {
                                Icon(Icons.Filled.Menu, contentDescription = "Menu")
                            }
                        },
                    )
                },
                floatingActionButton = {
                    FloatingActionButton(onClick = { /* TODO: Otwórz modal dodawania */ }) {
                        Icon(Icons.Filled.Add, contentDescription = "Dodaj wydatek")
                    }
                },
            ) { innerPadding ->
                Box(modifier = Modifier.padding(innerPadding)) {
                    DashboardScreen();
                }
            }
        }
    }
}