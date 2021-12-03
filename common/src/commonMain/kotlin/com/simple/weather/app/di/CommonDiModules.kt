package com.simple.weather.app.di

val commonDiModules = listOf(
    commonExpectActualModule,
    databaseModule,
    networkModule,
    dataModule,
    repositoriesModule,
    domainModule
)