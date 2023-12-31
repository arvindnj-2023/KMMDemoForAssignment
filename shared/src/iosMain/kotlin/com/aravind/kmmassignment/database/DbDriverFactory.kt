package com.aravind.kmmassignment.database

import com.aravind.kmmassignment.db.KmmDemoDatabase
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import org.koin.core.scope.Scope

internal actual fun Scope.createDriver(databaseName: String): SqlDriver =
    NativeSqliteDriver(KmmDemoDatabase.Schema, databaseName)