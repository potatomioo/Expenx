package com.potatomioo.expenx.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import com.potatomioo.expenx.expenx.Expense
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.util.UUID

class expenxViewModel : ViewModel() {

    private val fireStore = FirebaseFirestore.getInstance()
    private val expensesCollection = fireStore.collection("expenses")

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error : StateFlow<String?> = _error.asStateFlow()

    fun addExpense(amount : Double, description : String){
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val expense = Expense(
                    id = UUID.randomUUID().toString(),
                    amount = amount,
                    description = description
                )
                expensesCollection.document(expense.id).set(expense).await()
            }
            catch (e : Exception){
                print("$e")
                _error.value = "Failed"
            }
            finally {
                _isLoading.value = false
            }
        }
    }
}