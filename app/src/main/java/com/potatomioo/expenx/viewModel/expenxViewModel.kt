package com.potatomioo.expenx.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.potatomioo.expenx.expenx.Expense
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.util.UUID

class ExpenxViewModel : ViewModel() {

    private val firestore = Firebase.firestore
    private val expensesCollection = firestore.collection("expenses")

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    fun addExpense(amount: Double, description: String) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val expense = Expense(
                    id = UUID.randomUUID().toString(),
                    amount = amount,
                    description = description
                )
                expensesCollection.add(expense).await()
                _error.value = null
            } catch (e: Exception) {
                _error.value = "Failed to add expense: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}