package com.example.caveavin.data

import com.example.caveavin.data.model.Bottle
import com.example.caveavin.data.model.Tasting
import com.example.caveavin.data.model.WineType
import kotlinx.coroutines.flow.first

/**
 * Utilitaire pour peupler la base de données avec des exemples
 * Utilisez cette classe pour tester l'application rapidement
 */
class SampleDataGenerator(private val repository: WineCellarRepository) {
    
    suspend fun generateSampleData() {
        // Vérifier si la base est vide
        val existingBottles = repository.getAllBottles().first()
        if (existingBottles.isNotEmpty()) {
            return // Ne pas ajouter d'exemples si des données existent déjà
        }
        
        // Bouteilles exemples
        val sampleBottles = listOf(
            Bottle(
                name = "Château Margaux",
                appellation = "Margaux",
                vintage = 2015,
                region = "Bordeaux",
                grapeVariety = "Cabernet Sauvignon, Merlot",
                type = WineType.RED,
                quantity = 3,
                purchasePrice = 450.0,
                location = "Cave principale, Casier A1",
                peakYear = 2030,
                photoPath = null
            ),
            Bottle(
                name = "Chablis Grand Cru",
                appellation = "Chablis Grand Cru",
                vintage = 2019,
                region = "Bourgogne",
                grapeVariety = "Chardonnay",
                type = WineType.WHITE,
                quantity = 6,
                purchasePrice = 75.0,
                location = "Cave principale, Casier B2",
                peakYear = 2027,
                photoPath = null
            ),
            Bottle(
                name = "Châteauneuf-du-Pape",
                appellation = "Châteauneuf-du-Pape",
                vintage = 2018,
                region = "Vallée du Rhône",
                grapeVariety = "Grenache, Syrah, Mourvèdre",
                type = WineType.RED,
                quantity = 4,
                purchasePrice = 55.0,
                location = "Cave principale, Casier A2",
                peakYear = 2028,
                photoPath = null
            ),
            Bottle(
                name = "Sancerre",
                appellation = "Sancerre",
                vintage = 2021,
                region = "Loire",
                grapeVariety = "Sauvignon Blanc",
                type = WineType.WHITE,
                quantity = 12,
                purchasePrice = 18.0,
                location = "Cave secondaire, Étagère 1",
                peakYear = 2024,
                photoPath = null
            ),
            Bottle(
                name = "Champagne Bollinger",
                appellation = "Champagne",
                vintage = 2012,
                region = "Champagne",
                grapeVariety = "Pinot Noir, Chardonnay",
                type = WineType.SPARKLING,
                quantity = 2,
                purchasePrice = 95.0,
                location = "Cave principale, Casier C1",
                peakYear = 2025,
                photoPath = null
            ),
            Bottle(
                name = "Côtes de Provence",
                appellation = "Côtes de Provence",
                vintage = 2022,
                region = "Provence",
                grapeVariety = "Grenache, Cinsault",
                type = WineType.ROSE,
                quantity = 8,
                purchasePrice = 12.0,
                location = "Cave secondaire, Étagère 2",
                peakYear = 2023,
                photoPath = null
            ),
            Bottle(
                name = "Pomerol",
                appellation = "Pomerol",
                vintage = 2016,
                region = "Bordeaux",
                grapeVariety = "Merlot, Cabernet Franc",
                type = WineType.RED,
                quantity = 2,
                purchasePrice = 180.0,
                location = "Cave principale, Casier A1",
                peakYear = 2031,
                photoPath = null
            ),
            Bottle(
                name = "Pouilly-Fuissé",
                appellation = "Pouilly-Fuissé",
                vintage = 2020,
                region = "Bourgogne",
                grapeVariety = "Chardonnay",
                type = WineType.WHITE,
                quantity = 5,
                purchasePrice = 32.0,
                location = "Cave principale, Casier B3",
                peakYear = 2026,
                photoPath = null
            ),
            Bottle(
                name = "Hermitage",
                appellation = "Hermitage",
                vintage = 2017,
                region = "Vallée du Rhône",
                grapeVariety = "Syrah",
                type = WineType.RED,
                quantity = 3,
                purchasePrice = 85.0,
                location = "Cave principale, Casier A3",
                peakYear = 2032,
                photoPath = null
            ),
            Bottle(
                name = "Condrieu",
                appellation = "Condrieu",
                vintage = 2021,
                region = "Vallée du Rhône",
                grapeVariety = "Viognier",
                type = WineType.WHITE,
                quantity = 4,
                purchasePrice = 42.0,
                location = "Cave principale, Casier B1",
                peakYear = 2025,
                photoPath = null
            )
        )
        
        // Insérer les bouteilles
        sampleBottles.forEach { bottle ->
            val bottleId = repository.insertBottle(bottle)
            
            // Ajouter quelques dégustations d'exemple pour certaines bouteilles
            if (bottleId % 3L == 0L) {
                val sampleTasting = Tasting(
                    bottleId = bottleId,
                    date = System.currentTimeMillis() - (1000L * 60 * 60 * 24 * 30), // Il y a 30 jours
                    rating = 4.5f,
                    notes = "Excellent vin avec une belle structure. Arômes complexes et équilibrés.",
                    aromas = "Fruits rouges, épices, notes boisées",
                    foodPairing = "Viande rouge grillée, fromages affinés"
                )
                repository.insertTasting(sampleTasting)
            }
        }
    }
    
    suspend fun clearAllData() {
        val bottles = repository.getAllBottles().first()
        bottles.forEach { bottle ->
            repository.deleteBottle(bottle)
        }
    }
}
