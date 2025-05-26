package com.expense.book.view.navigation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.expense.book.view.componenets.TextToggleSwitch
import com.expense.book.viewmodels.DataEntryViewModel
import com.google.android.material.chip.ChipGroup

@Composable
fun DataEntryScreen(
    onSave: () -> Unit,
    viewModel: DataEntryViewModel = hiltViewModel()
) {
    DataEntryScreenContent(onBack = onSave, viewModel = viewModel)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DataEntryScreenContent(
    onBack: () -> Unit,
    viewModel: DataEntryViewModel
) {
    // Collect StateFlow values from the ViewModel
    val allTypes by viewModel.allTypes.collectAsState()
//    val selectedType by viewModel.selectedExpenseCategory.collectAsState()
    val selectedType by viewModel.selectedEntryType.collectAsState()
    val buyerName by viewModel.buyerName.collectAsState()
    val quantity by viewModel.quantity.collectAsState()
    val price by viewModel.price.collectAsState()
    val amount by viewModel.amount.collectAsState()
    val description by viewModel.description.collectAsState()
    var expanded by remember { mutableStateOf(false) }


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Add Data") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back")
                    }
                },
                actions = {
                    IconButton(
                        onClick = {
                            //TODO: Implement save logic
//                            if (selectedType?.type == "Expense") viewModel.insertExpense()
//                            else if (selectedType?.type == "Income") viewModel.insertIncome()
                            onBack()
                        }
                    ) {
                        Icon(Icons.Filled.Check, contentDescription = "Done")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
                .imePadding()
                .navigationBarsPadding()
        ){
            TextToggleSwitch(
                firstOption = "Income",
                secondOption = "Expense",
                isFirstSelected = selectedType == DataEntryViewModel.ENTRY_TYPE.INCOME,
                onToggle = { isFirstSelected ->
                    viewModel.updateSelectedEntryType(
                        if (isFirstSelected) DataEntryViewModel.ENTRY_TYPE.INCOME
                        else DataEntryViewModel.ENTRY_TYPE.EXPENSE
                    )
                }
            )
        }



/*        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
                .imePadding()
                .navigationBarsPadding()
        ) {

            Box(modifier = Modifier.fillMaxWidth()) {
                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = { expanded = !expanded }
                ) {
                    OutlinedTextField(
                        value = selectedType?.categoryName ?: "Select Type",
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Type") },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                        modifier = Modifier
                            .menuAnchor(MenuAnchorType.PrimaryNotEditable)
                            .fillMaxWidth(),
                    )

                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        allTypes.forEach { type ->
                            androidx.compose.material3.DropdownMenuItem(
                                text = {
                                    Text(text = type.categoryName)
                                },
                                onClick = {
                                    viewModel.updateSelectedType(type)
                                    expanded = false
                                }
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Income Input Fields
            if (selectedType?.type == "Income") {
                OutlinedTextField(
                    value = buyerName,
                    onValueChange = { viewModel.updateBuyerName(it) },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "Buyer Name") }
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = quantity,
                    onValueChange = { viewModel.updateQuantity(it) },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "Quantity") }
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = price,
                    onValueChange = { viewModel.updatePrice(it) },
                    modifier = Modifier.fillMaxWidth(), label = { Text(text = "Price") }
                )

                Spacer(modifier = Modifier.height(16.dp))
            }
            // Expense Input Fields
            if (selectedType?.type == "Expense") {
                OutlinedTextField(
                    value = amount,
                    onValueChange = { viewModel.updateAmount(it) },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "Amount") }
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = description,
                    onValueChange = { viewModel.updateDescription(it) },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "Description") }
                )

            }
        }*/
    }
}