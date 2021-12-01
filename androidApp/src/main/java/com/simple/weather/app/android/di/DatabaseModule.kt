package com.simple.weather.app.android.di

import android.content.Context
import com.simple.weather.app.android.Database
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    factory { SqliteDriverProvider(androidContext()).getDriver() }
    single { Database(get()) }
}

class SqliteDriverProvider(
    private val context: Context
) {

    fun getDriver(): SqlDriver = AndroidSqliteDriver(Database.Schema, context, DB_NAME)

    companion object {
        private const val DB_NAME = "SimpleWeatherApp.db"
    }
}