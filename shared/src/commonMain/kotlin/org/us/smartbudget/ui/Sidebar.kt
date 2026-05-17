package org.us.smartbudget.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Sidebar(){
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .width(200.dp)
            .background(Color.LightGray)
            .padding(16.dp)
    ) {
        Text("Tu będzie menu")
        Spacer(modifier = Modifier.height(32.dp))
        Text("Pulpit")
        Text("Historia")
    }
}