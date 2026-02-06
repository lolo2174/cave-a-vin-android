# Cave à Vin - Application Android

Application Android de gestion de cave à vin développée en Kotlin avec Jetpack Compose.

## 📱 Fonctionnalités

### Gestion de l'inventaire
- ✅ Ajout de bouteilles avec informations détaillées (nom, appellation, millésime, région, cépage, type)
- ✅ Modification et suppression de bouteilles
- ✅ Gestion des quantités (ajout/retrait)
- ✅ Stockage de l'emplacement physique dans la cave
- ✅ Prix d'achat et calcul de la valeur totale
- ✅ Année d'apogée recommandée

### Recherche et filtres
- ✅ Recherche par nom, appellation ou région
- ✅ Filtres par type de vin (Rouge, Blanc, Rosé, Effervescent)
- ✅ Affichage en liste avec toutes les informations importantes

### Dégustations
- ✅ Enregistrement des dégustations avec notes
- ✅ Système de notation sur 5 étoiles
- ✅ Notes d'arômes et accords mets-vins
- ✅ Historique des dégustations par bouteille

### Statistiques
- ✅ Nombre total de bouteilles
- ✅ Valeur totale de la cave
- ✅ Prix moyen par bouteille
- ✅ Répartition par type de vin (avec pourcentages)
- ✅ Top 5 des régions
- ✅ Millésimes les plus récents

## 🏗️ Architecture

### Technologies utilisées
- **Langage** : Kotlin
- **UI** : Jetpack Compose (Material Design 3)
- **Base de données** : Room (SQLite)
- **Architecture** : MVVM (Model-View-ViewModel)
- **Navigation** : Navigation Compose
- **Gestion d'état** : StateFlow et Compose State

### Structure du projet
```
app/src/main/java/com/example/caveavin/
├── data/
│   ├── model/           # Entités de données (Bottle, Tasting, WineType)
│   ├── dao/             # Data Access Objects (BottleDao, TastingDao)
│   ├── database/        # Configuration Room (Database, Converters)
│   └── WineCellarRepository.kt
├── viewmodel/
│   └── WineCellarViewModel.kt
├── ui/
│   ├── screens/         # Écrans de l'application
│   │   ├── BottleListScreen.kt
│   │   ├── AddEditBottleScreen.kt
│   │   ├── BottleDetailScreen.kt
│   │   ├── AddTastingScreen.kt
│   │   └── StatisticsScreen.kt
│   └── theme/           # Thème Material Design 3
└── MainActivity.kt
```

## 🔧 Installation et compilation

### Prérequis
- Android Studio Hedgehog (2023.1.1) ou supérieur
- JDK 17
- Android SDK (API 24 minimum, API 34 cible)
- Gradle 8.2

### Étapes d'installation

1. **Ouvrir le projet dans Android Studio**
   ```
   File > Open > Sélectionner le dossier cave-a-vin-app
   ```

2. **Synchroniser les dépendances Gradle**
   - Android Studio devrait automatiquement détecter le projet et proposer de synchroniser
   - Ou cliquer sur : File > Sync Project with Gradle Files

3. **Créer les icônes de l'application (optionnel)**
   - Placer une icône dans `app/src/main/res/mipmap-*/`
   - Ou utiliser : Right-click sur res > New > Image Asset

4. **Compiler l'application**
   - Build > Make Project (Ctrl+F9)
   - Build > Build Bundle(s) / APK(s) > Build APK(s)

5. **Installer sur un appareil**
   - Connecter un appareil Android en mode développeur
   - Ou créer un émulateur : Tools > Device Manager
   - Run > Run 'app' (Shift+F10)

### Générer un APK signé pour distribution

1. Build > Generate Signed Bundle / APK
2. Sélectionner APK
3. Créer ou sélectionner une clé de signature
4. Choisir la variante "release"
5. L'APK sera généré dans `app/release/`

## 📊 Base de données

### Structure

**Table `bottles`**
- id (PRIMARY KEY)
- name (nom du vin)
- appellation
- vintage (millésime)
- region
- grapeVariety (cépage)
- type (enum: RED, WHITE, ROSE, SPARKLING)
- quantity
- purchasePrice
- location (emplacement)
- peakYear (apogée)
- photoPath (futur usage)
- createdAt

**Table `tastings`**
- id (PRIMARY KEY)
- bottleId (FOREIGN KEY → bottles.id)
- date
- rating (0-5)
- notes
- aromas
- foodPairing

### Stockage local
- Toutes les données sont stockées localement dans une base SQLite
- Emplacement : `/data/data/com.example.caveavin/databases/wine_cellar_database`
- Backup automatique via Android Auto Backup (Android 6+)

## 🎨 Interface utilisateur

### Thème
- Material Design 3 avec palette de couleurs inspirée du vin
- Mode clair par défaut (mode sombre supporté)
- Couleurs principales : Rouge bordeaux et or

### Écrans principaux

1. **Liste des bouteilles**
   - Barre de recherche
   - Filtres par type
   - Cards affichant les informations essentielles
   - Bouton flottant pour ajouter une bouteille

2. **Détails d'une bouteille**
   - Toutes les informations de la bouteille
   - Gestion rapide de la quantité (+/-)
   - Liste des dégustations
   - Actions : Modifier, Supprimer

3. **Ajout/Édition de bouteille**
   - Formulaire complet avec validation
   - Sélection du type via menu déroulant
   - Tous les champs importants

4. **Statistiques**
   - Vue d'ensemble de la cave
   - Graphiques de répartition
   - Top régions et millésimes

5. **Ajout de dégustation**
   - Notation par étoiles
   - Zones de texte pour notes détaillées
   - Champs optionnels pour arômes et accords

## 🚀 Évolutions futures possibles

### Fonctionnalités à ajouter
- [ ] Capture de photo de l'étiquette
- [ ] Scan de code-barres/QR code
- [ ] Export CSV/PDF de l'inventaire
- [ ] Widget Android pour accès rapide
- [ ] Alertes pour bouteilles à consommer
- [ ] Suggestions d'accords mets-vins
- [ ] Mode sombre persistant
- [ ] Backup manuel/restauration
- [ ] Partage de cave entre utilisateurs (avec cloud)
- [ ] Graphiques plus avancés
- [ ] Cartes des régions viticoles
- [ ] Intégration API de prix du marché

### Améliorations techniques
- [ ] Tests unitaires et d'intégration
- [ ] CI/CD avec GitHub Actions
- [ ] Localisation (EN, ES, IT)
- [ ] Accessibility improvements
- [ ] Performance optimizations
- [ ] Pagination pour grandes caves

## 📝 Notes de développement

### Dépendances principales
```kotlin
// Jetpack Compose
implementation("androidx.compose.ui:ui")
implementation("androidx.compose.material3:material3")

// Room Database
implementation("androidx.room:room-runtime:2.6.0")
implementation("androidx.room:room-ktx:2.6.0")
ksp("androidx.room:room-compiler:2.6.0")

// Navigation
implementation("androidx.navigation:navigation-compose:2.7.5")

// ViewModel
implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")
```

### Versions
- Kotlin : 1.9.20
- Compose : 2023.10.01
- Room : 2.6.0
- Minimum SDK : 24 (Android 7.0)
- Target SDK : 34 (Android 14)

## 📄 Licence

Application de démonstration développée pour un usage personnel.

## 🤝 Contribution

Pour toute suggestion ou amélioration, n'hésitez pas à créer une issue ou une pull request.

## 📧 Contact

Pour toute question concernant l'application.

---

**Développé avec ❤️ et 🍷**
