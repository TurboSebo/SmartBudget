package org.us.smartbudget

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import java.io.File
import org.us.smartbudget.database.SmartBudgetDatabase
import org.us.smartbudget.di.ServiceLocator

fun main() = application {
    // Inicjalizacja bazy danych
    val databaseFile = File(System.getProperty("user.home") + "/.smartbudget/smartbudget.db")
    databaseFile.parentFile?.mkdirs()

    val driver = JdbcSqliteDriver("jdbc:sqlite:${databaseFile.absolutePath}")
    SmartBudgetDatabase.Schema.create(driver)
    val database = SmartBudgetDatabase(driver)
    ServiceLocator.initializeDatabase(database)

    Window(
        onCloseRequest = ::exitApplication,
        title = "SmartBudget",
    ) {
        App()
    }
}