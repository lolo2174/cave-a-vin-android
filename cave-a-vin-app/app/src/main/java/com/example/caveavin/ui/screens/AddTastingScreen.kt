package com.example.caveavin.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.caveavin.data.model.Tasting

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTastingScreen(
    bottleId: Long,
    onSave: (Tasting) -> Unit,
    onBack: () -> Unit
) {
    var rating by remember { mutableFloatStateOf(3f) }
    var notes by remember { mutableStateOf("") }
    var aromas by remember { mutableStateOf("") }
    var foodPairing by remember { mutableStateOf("") }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Nouvelle dégustation") },
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
                    val tasting = Tasting(
                        bottleId = bottleId,
                        date = System.currentTimeMillis(),
                        rating = rating,
                        notes = notes,
                        aromas = aromas.ifBlank { null },
                        foodPairing = foodPairing.ifBlank { null }
                    )
                    onSave(tasting)
                    onBack()
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
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // Rating
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        text = "Note globale",
                        style = MaterialTheme.typography.titleLarge
                    )
                    
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        for (i in 1..5) {
                            IconButton(
                                onClick = { rating = i.toFloat() }
                            ) {
                                Icon(
                                    imageVector = if (i <= rating) Icons.Filled.Star else Icons.Outlined.StarOutline,
                                    contentDescription = "$i étoiles",
                                    tint = if (i <= rating) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant,
                                    modifier = Modifier.size(40.dp)
                                )
                            }
                        }
                    }
                    
                    Text(
                        text = "${rating.toInt()}/5",
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
            
            // Notes
            OutlinedTextField(
                value = notes,
                onValueChange = { notes = it },
                label = { Text("Notes de dégustation") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                placeholder = { Text("Décrivez vos impressions...") },
                maxLines = 6
            )
            
            // Aromas
            OutlinedTextField(
                value = aromas,
                onValueChange = { aromas = it },
                label = { Text("Arômes") },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Fruits rouges, épices, boisé...") },
                maxLines = 2
            )
            
            // Food pairing
            OutlinedTextField(
                value = foodPairing,
                onValueChange = { foodPairing = it },
                label = { Text("Accord mets-vin") },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Viande rouge, fromage...") },
                maxLines = 2
            )
            
            Spacer(modifier = Modifier.height(80.dp))
        }
    }
}
