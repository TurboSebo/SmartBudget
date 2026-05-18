package org.us.smartbudget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.us.smartbudget.ui.DashboardScreen
import org.us.smartbudget.ui.Sidebar

@Composable
@Preview
fun App() {
    MaterialTheme {
        var DrawerState = rememberDrawerState(DrawerValue.Closed);
        var scope = rememberCoroutineScope();

        DashboardScreen();
        Sidebar(
            drawerState = DrawerState,
            content = {
                DashboardScreen()
            }
        )


    }
}