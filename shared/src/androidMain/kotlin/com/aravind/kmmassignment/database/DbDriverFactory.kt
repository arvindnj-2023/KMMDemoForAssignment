package com.aravind.kmmassignment.database

import com.aravind.kmmassignment.db.KmmDemoDatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import org.koin.android.ext.koin.androidContext
import org.koin.core.scope.Scope

internal actual fun Scope.createDriver(databaseName: String): SqlDriver =
    AndroidSqliteDriver(KmmDemoDatabase.Schema, androidContext(), databaseName)