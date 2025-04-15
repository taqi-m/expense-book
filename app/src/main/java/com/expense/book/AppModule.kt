package com.expense.book

import android.content.Context
import androidx.room.Room
import com.expense.book.model.data.AppPreferences
import com.expense.book.model.data.database.DataRepository
import com.expense.book.model.data.database.local.AppDatabase
import com.expense.book.model.data.database.local.dao.CategoryDao
import com.expense.book.model.data.database.local.dao.ExpenseDao
import com.expense.book.model.data.database.local.dao.IncomeDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context, AppDatabase::class.java, "expense_tracking_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideCategoryDao(database: AppDatabase): CategoryDao {
        return database.categoryDao()
    }

    @Provides
    @Singleton
    fun provideIncomeDao(database: AppDatabase): IncomeDao {
        return database.incomeDao()
    }

    @Provides
    @Singleton
    fun provideExpenseDao(database: AppDatabase): ExpenseDao {
        return database.expenseDao()
    }

    @Provides
    @Singleton
    fun provideAppPreferences(@ApplicationContext context: Context): AppPreferences {
        return AppPreferences(context)
    }

    @Provides
    @Singleton
    fun provideDataRepository(
        categoryDao: CategoryDao,
        incomeDao: IncomeDao,
        expenseDao: ExpenseDao,
        appPreferences: AppPreferences
    ): DataRepository {
        return DataRepository(categoryDao, incomeDao, expenseDao, appPreferences)
    }
}
