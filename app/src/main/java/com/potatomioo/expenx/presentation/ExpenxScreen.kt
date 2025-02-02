package com.potatomioo.expenx.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.potatomioo.expenx.viewModel.expenxViewModel

@Composable
fun AppScreen(
    viewModel: expenxViewModel = viewModel()
) {

    var amount by remember { mutableStateOf(0.0) }
    var description by remember { mutableStateOf("null") }

    val isLoading = viewModel.isLoading.collectAsState()
    val error = viewModel.error.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Add New Expense",
            modifier = Modifier.padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = amount.toString(),
            onValueChange = { amount = it.toDouble() },
            label = { Text("Amount") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal
            ),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                if (amount != null && description.isNotBlank()) {
                    viewModel.addExpense(amount, description)
                    amount = 0.0
                    description = ""
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = !isLoading.value && amount!= null && description.isNotBlank()
        ) {
            if (isLoading.value){
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp),
                )
            } else {
                Text("Add Expense")
            }
        }
        error?.let { errorMessage ->
            Text(
                text = "$errorMessage",
                color = Color.Red,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
        }
}