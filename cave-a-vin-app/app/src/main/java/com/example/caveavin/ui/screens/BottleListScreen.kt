package com.example.caveavin.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.caveavin.data.model.Bottle
import com.example.caveavin.data.model.WineType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottleListScreen(
    bottles: List<Bottle>,
    searchQuery: String,
    selectedType: WineType?,
    onSearchQueryChange: (String) -> Unit,
    onTypeFilterChange: (WineType?) -> Unit,
    onBottleClick: (Bottle) -> Unit,
    onAddBottleClick: () -> Unit,
    onNavigateToStats: () -> Unit
) {
    var showFilterMenu by remember { mutableStateOf(false) }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Ma Cave à Vin") },
                actions = {
                    IconButton(onClick = onNavigateToStats) {
                        Icon(Icons.Default.BarChart, contentDescription = "Statistiques")
                    }
                    IconButton(onClick = { showFilterMenu = true }) {
                        Icon(Icons.Default.FilterList, contentDescription = "Filtrer")
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
                onClick = onAddBottleClick,
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(Icons.Default.Add, contentDescription = "Ajouter une bouteille")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Search bar
            OutlinedTextField(
                value = searchQuery,
                onValueChange = onSearchQueryChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                placeholder = { Text("Rechercher...") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                trailingIcon = {
                    if (searchQuery.isNotEmpty()) {
                        IconButton(onClick = { onSearchQueryChange("") }) {
                            Icon(Icons.Default.Clear, contentDescription = "Effacer")
                        }
                    }
                },
                singleLine = true
            )
            
            // Type filter chips
            if (selectedType != null) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    FilterChip(
                        selected = true,
                        onClick = { onTypeFilterChange(null) },
                        label = { Text(getWineTypeName(selectedType)) },
                        trailingIcon = {
                            Icon(
                                Icons.Default.Close,
                                contentDescription = "Supprimer le filtre",
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
            
            // Bottle list
            if (bottles.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = if (searchQuery.isEmpty() && selectedType == null) 
                            "Aucune bouteille dans votre cave.\nAppuyez sur + pour en ajouter."
                        else 
                            "Aucune bouteille trouvée.",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(bottles, key = { it.id }) { bottle ->
                        BottleCard(
                            bottle = bottle,
                            onClick = { onBottleClick(bottle) }
                        )
                    }
                }
            }
        }
        
        // Filter menu
        DropdownMenu(
            expanded = showFilterMenu,
            onDismissRequest = { showFilterMenu = false }
        ) {
            DropdownMenuItem(
                text = { Text("Tous les vins") },
                onClick = {
                    onTypeFilterChange(null)
                    showFilterMenu = false
                },
                leadingIcon = { Icon(Icons.Default.WineBar, contentDescription = null) }
            )
            Divider()
            WineType.entries.forEach { type ->
                DropdownMenuItem(
                    text = { Text(getWineTypeName(type)) },
                    onClick = {
                        onTypeFilterChange(type)
                        showFilterMenu = false
                    },
                    leadingIcon = { Icon(Icons.Default.Circle, contentDescription = null) }
                )
            }
        }
    }
}

@Composable
fun BottleCard(
    bottle: Bottle,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = bottle.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = bottle.appellation,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "${bottle.vintage}",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Text(
                        text = "•",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Text(
                        text = bottle.region,
                        style = MaterialTheme.typography.bodySmall
                    )
                    Text(
                        text = "•",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Text(
                        text = getWineTypeName(bottle.type),
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Qté: ${bottle.quantity}",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "${String.format("%.2f", bottle.purchasePrice)} €",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

fun getWineTypeName(type: WineType): String {
    return when (type) {
        WineType.RED -> "Rouge"
        WineType.WHITE -> "Blanc"
        WineType.ROSE -> "Rosé"
        WineType.SPARKLING -> "Effervescent"
    }
}
