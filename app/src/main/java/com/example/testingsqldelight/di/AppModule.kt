package com.example.testingsqldelight.di

import android.app.Application
import com.example.testingsqldelight.PersonDatabase
import com.example.testingsqldelight.data.PersonDataSource
import com.example.testingsqldelight.data.PersonDataSourceImpl
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSqlDriver(app: Application): SqlDriver{
        return AndroidSqliteDriver(
            schema = PersonDatabase.Schema,
            context = app,
            name = "person.db"
        )
    }

    @Provides
    @Singleton
    fun providePersonDataSource(driver: SqlDriver): PersonDataSource{
        return PersonDataSourceImpl(PersonDatabase(driver))
    }

}