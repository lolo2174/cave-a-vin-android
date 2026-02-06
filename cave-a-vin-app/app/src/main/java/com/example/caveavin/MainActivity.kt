package com.example.caveavin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.caveavin.data.WineCellarRepository
import com.example.caveavin.data.database.WineCellarDatabase
import com.example.caveavin.ui.screens.*
import com.example.caveavin.ui.theme.CaveAVinTheme
import com.example.caveavin.viewmodel.WineCellarViewModel
import com.example.caveavin.viewmodel.WineCellarViewModelFactory
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        
        val database = WineCellarDatabase.getDatabase(applicationContext)
        val repository = WineCellarRepository(
            database.bottleDao(),
            database.tastingDao()
        )
        
        setContent {
            CaveAVinTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    WineCellarApp(repository = repository)
                }
            }
        }
    }
}

@Composable
fun WineCellarApp(repository: WineCellarRepository) {
    val navController = rememberNavController()
    val viewModel: WineCellarViewModel = viewModel(
        factory = WineCellarViewModelFactory(repository)
    )
    
    val bottles by viewModel.bottles.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()
    val selectedType by viewModel.selectedType.collectAsState()
    val totalBottles by viewModel.totalBottles.collectAsState()
    val totalValue by viewModel.totalValue.collectAsState()
    
    NavHost(
        navController = navController,
        startDestination = "bottle_list"
    ) {
        // Bottle List Screen
        composable("bottle_list") {
            BottleListScreen(
                bottles = bottles,
                searchQuery = searchQuery,
                selectedType = selectedType,
                onSearchQueryChange = { viewModel.setSearchQuery(it) },
                onTypeFilterChange = { viewModel.setSelectedType(it) },
                onBottleClick = { bottle ->
                    navController.navigate("bottle_detail/${bottle.id}")
                },
                onAddBottleClick = {
                    navController.navigate("add_bottle")
                },
                onNavigateToStats = {
                    navController.navigate("statistics")
                }
            )
        }
        
        // Add Bottle Screen
        composable("add_bottle") {
            AddEditBottleScreen(
                bottle = null,
                onSave = { bottle ->
                    viewModel.insertBottle(bottle)
                },
                onBack = { navController.popBackStack() }
            )
        }
        
        // Edit Bottle Screen
        composable(
            route = "edit_bottle/{bottleId}",
            arguments = listOf(navArgument("bottleId") { type = NavType.LongType })
        ) { backStackEntry ->
            val bottleId = backStackEntry.arguments?.getLong("bottleId") ?: return@composable
            
            var bottle by remember { mutableStateOf<com.example.caveavin.data.model.Bottle?>(null) }
            
            LaunchedEffect(bottleId) {
                bottle = viewModel.getBottleById(bottleId)
            }
            
            bottle?.let { currentBottle ->
                AddEditBottleScreen(
                    bottle = currentBottle,
                    onSave = { updatedBottle ->
                        viewModel.updateBottle(updatedBottle)
                    },
                    onBack = { navController.popBackStack() }
                )
            }
        }
        
        // Bottle Detail Screen
        composable(
            route = "bottle_detail/{bottleId}",
            arguments = listOf(navArgument("bottleId") { type = NavType.LongType })
        ) { backStackEntry ->
            val bottleId = backStackEntry.arguments?.getLong("bottleId") ?: return@composable
            
            var bottle by remember { mutableStateOf<com.example.caveavin.data.model.Bottle?>(null) }
            val tastings by viewModel.getTastingsForBottle(bottleId).collectAsState(initial = emptyList())
            
            LaunchedEffect(bottleId) {
                bottle = viewModel.getBottleById(bottleId)
            }
            
            bottle?.let { currentBottle ->
                BottleDetailScreen(
                    bottle = currentBottle,
                    tastings = tastings,
                    onBack = { navController.popBackStack() },
                    onEdit = { navController.navigate("edit_bottle/$bottleId") },
                    onDelete = {
                        viewModel.deleteBottle(currentBottle)
                        navController.popBackStack()
                    },
                    onAddTasting = {
                        navController.navigate("add_tasting/$bottleId")
                    },
                    onUpdateQuantity = { newQuantity ->
                        viewModel.updateQuantity(bottleId, newQuantity)
                    }
                )
            }
        }
        
        // Add Tasting Screen
        composable(
            route = "add_tasting/{bottleId}",
            arguments = listOf(navArgument("bottleId") { type = NavType.LongType })
        ) { backStackEntry ->
            val bottleId = backStackEntry.arguments?.getLong("bottleId") ?: return@composable
            
            AddTastingScreen(
                bottleId = bottleId,
                onSave = { tasting ->
                    viewModel.insertTasting(tasting)
                },
                onBack = { navController.popBackStack() }
            )
        }
        
        // Statistics Screen
        composable("statistics") {
            StatisticsScreen(
                bottles = bottles,
                totalBottles = totalBottles,
                totalValue = totalValue,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
