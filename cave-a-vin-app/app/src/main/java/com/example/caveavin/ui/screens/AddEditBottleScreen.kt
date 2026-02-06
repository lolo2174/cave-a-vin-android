package com.example.caveavin.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.caveavin.data.model.Bottle
import com.example.caveavin.data.model.WineType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditBottleScreen(
    bottle: Bottle? = null,
    onSave: (Bottle) -> Unit,
    onBack: () -> Unit
) {
    var name by remember { mutableStateOf(bottle?.name ?: "") }
    var appellation by remember { mutableStateOf(bottle?.appellation ?: "") }
    var vintage by remember { mutableStateOf(bottle?.vintage?.toString() ?: "") }
    var region by remember { mutableStateOf(bottle?.region ?: "") }
    var grapeVariety by remember { mutableStateOf(bottle?.grapeVariety ?: "") }
    var wineType by remember { mutableStateOf(bottle?.type ?: WineType.RED) }
    var quantity by remember { mutableStateOf(bottle?.quantity?.toString() ?: "1") }
    var purchasePrice by remember { mutableStateOf(bottle?.purchasePrice?.toString() ?: "") }
    var location by remember { mutableStateOf(bottle?.location ?: "") }
    var peakYear by remember { mutableStateOf(bottle?.peakYear?.toString() ?: "") }
    
    var showTypeMenu by remember { mutableStateOf(false) }
    var showError by remember { mutableStateOf(false) }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (bottle == null) "Ajouter une bouteille" else "Modifier la bouteille") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Retour")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    if (name.isBlank() || appellation.isBlank() || vintage.isBlank() || 
                        region.isBlank() || grapeVariety.isBlank() || quantity.isBlank()) {
                        showError = true
                    } else {
                        val newBottle = Bottle(
                            id = bottle?.id ?: 0,
                            name = name,
                            appellation = appellation,
                            vintage = vintage.toIntOrNull() ?: 0,
                            region = region,
                            grapeVariety = grapeVariety,
                            type = wineType,
                            quantity = quantity.toIntOrNull() ?: 1,
                            purchasePrice = purchasePrice.toDoubleOrNull() ?: 0.0,
                            location = location,
                            peakYear = peakYear.toIntOrNull(),
                            photoPath = bottle?.photoPath,
                            createdAt = bottle?.createdAt ?: System.currentTimeMillis()
                        )
                        onSave(newBottle)
                        onBack()
                    }
                }
            ) {
                Icon(Icons.Default.Save, contentDescription = "Enregistrer")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            if (showError) {
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.errorContainer
                    )
                ) {
                    Text(
                        text = "Veuillez remplir tous les champs obligatoires",
                        modifier = Modifier.padding(16.dp),
                        color = MaterialTheme.colorScheme.onErrorContainer
                    )
                }
            }
            
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Nom du vin *") },
                modifier = Modifier.fillMaxWidth(),
                isError = showError && name.isBlank()
            )
            
            OutlinedTextField(
                value = appellation,
                onValueChange = { appellation = it },
                label = { Text("Appellation *") },
                modifier = Modifier.fillMaxWidth(),
                isError = showError && appellation.isBlank()
            )
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = vintage,
                    onValueChange = { vintage = it },
                    label = { Text("Millésime *") },
                    modifier = Modifier.weight(1f),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    isError = showError && vintage.isBlank()
                )
                
                OutlinedTextField(
                    value = peakYear,
                    onValueChange = { peakYear = it },
                    label = { Text("Apogée") },
                    modifier = Modifier.weight(1f),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }
            
            OutlinedTextField(
                value = region,
                onValueChange = { region = it },
                label = { Text("Région *") },
                modifier = Modifier.fillMaxWidth(),
                isError = showError && region.isBlank()
            )
            
            OutlinedTextField(
                value = grapeVariety,
                onValueChange = { grapeVariety = it },
                label = { Text("Cépage *") },
                modifier = Modifier.fillMaxWidth(),
                isError = showError && grapeVariety.isBlank()
            )
            
            // Wine type selector
            ExposedDropdownMenuBox(
                expanded = showTypeMenu,
                onExpandedChange = { showTypeMenu = it }
            ) {
                OutlinedTextField(
                    value = getWineTypeName(wineType),
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Type de vin *") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor(),
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = showTypeMenu) }
                )
                
                ExposedDropdownMenu(
                    expanded = showTypeMenu,
                    onDismissRequest = { showTypeMenu = false }
                ) {
                    WineType.entries.forEach { type ->
                        DropdownMenuItem(
                            text = { Text(getWineTypeName(type)) },
                            onClick = {
                                wineType = type
                                showTypeMenu = false
                            }
                        )
                    }
                }
            }
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = quantity,
                    onValueChange = { quantity = it },
                    label = { Text("Quantité *") },
                    modifier = Modifier.weight(1f),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    isError = showError && quantity.isBlank()
                )
                
                OutlinedTextField(
                    value = purchasePrice,
                    onValueChange = { purchasePrice = it },
                    label = { Text("Prix (€)") },
                    modifier = Modifier.weight(1f),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                )
            }
            
            OutlinedTextField(
                value = location,
                onValueChange = { location = it },
                label = { Text("Emplacement") },
                modifier = Modifier.fillMaxWidth(),
                supportingText = { Text("Ex: Cave, Casier A3, Étagère 2") }
            )
            
            Spacer(modifier = Modifier.height(80.dp))
        }
    }
}
