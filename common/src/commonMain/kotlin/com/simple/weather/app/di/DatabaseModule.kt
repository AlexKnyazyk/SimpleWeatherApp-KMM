package com.simple.weather.app.di

import com.simple.weather.app.data.db.AppDatabase
import org.koin.dsl.module

val databaseModule = module {
    single { AppDatabase(get<SqliteDriverFactory>().createDriver()) }
}
