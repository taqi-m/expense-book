package com.expense.book.model.data

import android.content.Context
import androidx.core.content.edit

class AppPreferences(context: Context) {
    private val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)

    fun areDefaultTypesAdded(): Boolean {
        return sharedPreferences.getBoolean("default_types_added", false)
    }

    fun setDefaultTypesAdded() {
        sharedPreferences.edit() { putBoolean("default_types_added", true) }
    }
}