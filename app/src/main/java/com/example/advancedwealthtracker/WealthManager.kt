package com.ani.wealthtracker.model


object WealthManager {

    private const val NAME_LETTERS = 3
    private const val SURNAME_LETTERS = 9
    private const val DAY_OF_BIRTH = 19

    val K: Double = (NAME_LETTERS + SURNAME_LETTERS).toDouble() / DAY_OF_BIRTH


    fun calculateSavings(income: Double, expenses: Double): Double {
        return (income - expenses) * K
    }

    fun calculateNet(income: Double, expenses: Double): Double {
        return income - expenses
    }

    fun savingsRate(income: Double, expenses: Double): Double {
        if (income == 0.0) return 0.0
        return ((income - expenses) / income) * 100.0
    }

    fun expenseRatio(income: Double, expenses: Double): Double {
        if (income == 0.0) return 0.0
        return (expenses / income) * 100.0
    }

    fun isValidInput(value: String): Boolean {
        return value.isNotBlank() && value.toDoubleOrNull() != null && value.toDouble() >= 0
    }
}