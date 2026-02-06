# 🍷 Application Cave à Vin - Android

## 📱 Présentation du Projet

Application Android native complète de gestion de cave à vin, développée en **Kotlin** avec **Jetpack Compose** et **Material Design 3**.

### ✨ Fonctionnalités Implémentées

#### 🗂️ Gestion de l'Inventaire
✅ Ajout, modification et suppression de bouteilles  
✅ Informations complètes : nom, appellation, millésime, région, cépage, type  
✅ Gestion des quantités avec boutons +/-  
✅ Prix d'achat et calcul de valeur totale  
✅ Emplacement physique dans la cave  
✅ Année d'apogée recommandée  

#### 🔍 Recherche et Filtres
✅ Barre de recherche en temps réel  
✅ Recherche par nom, appellation ou région  
✅ Filtres par type de vin (Rouge, Blanc, Rosé, Effervescent)  
✅ Combinaison recherche + filtres  

#### 🍷 Dégustations
✅ Enregistrement de dégustations avec notes détaillées  
✅ Système de notation sur 5 étoiles  
✅ Notes d'arômes et accords mets-vins  
✅ Historique complet par bouteille  
✅ Date de dégustation automatique  

#### 📊 Statistiques Avancées
✅ Nombre total de bouteilles  
✅ Valeur totale de la cave  
✅ Prix moyen par bouteille  
✅ Répartition par type avec pourcentages et graphiques  
✅ Top 5 des régions  
✅ Millésimes les plus représentés  

### 🏗️ Architecture Technique

**Stack Technologique**
- **Langage** : Kotlin 1.9.20
- **UI Framework** : Jetpack Compose (Material Design 3)
- **Base de données** : Room (SQLite)
- **Architecture** : MVVM (Model-View-ViewModel)
- **Navigation** : Navigation Compose
- **Async** : Kotlin Coroutines + Flow
- **Injection de dépendances** : Manuel (simple et efficace)

**Structure du Code**
```
app/src/main/java/com/example/caveavin/
├── MainActivity.kt                    # Point d'entrée et navigation
├── data/
│   ├── model/                         # Entités Room
│   │   ├── Bottle.kt                  # Modèle bouteille
│   │   └── Tasting.kt                 # Modèle dégustation
│   ├── dao/                           # Data Access Objects
│   │   ├── BottleDao.kt               # Requêtes bouteilles
│   │   └── TastingDao.kt              # Requêtes dégustations
│   ├── database/
│   │   ├── WineCellarDatabase.kt      # Configuration Room
│   │   └── Converters.kt              # Type converters
│   ├── WineCellarRepository.kt        # Couche d'accès aux données
│   └── SampleDataGenerator.kt         # Données d'exemple
├── viewmodel/
│   └── WineCellarViewModel.kt         # Logique métier
└── ui/
    ├── screens/                       # Écrans Compose
    │   ├── BottleListScreen.kt        # Liste principale
    │   ├── BottleDetailScreen.kt      # Détails bouteille
    │   ├── AddEditBottleScreen.kt     # Ajout/édition
    │   ├── AddTastingScreen.kt        # Nouvelle dégustation
    │   └── StatisticsScreen.kt        # Statistiques
    └── theme/                         # Thème Material 3
        ├── Color.kt
        └── Theme.kt
```

### 📦 Contenu du Projet

**Fichiers Principaux**
- ✅ Code source complet (Kotlin)
- ✅ Configuration Gradle (build.gradle.kts)
- ✅ AndroidManifest.xml
- ✅ Ressources (strings.xml, themes.xml)
- ✅ Configuration Room Database
- ✅ ProGuard rules

**Documentation**
- ✅ README.md - Documentation technique complète
- ✅ GUIDE_UTILISATEUR.md - Guide utilisateur détaillé
- ✅ Commentaires dans le code

### 🚀 Installation et Utilisation

#### Prérequis
- Android Studio Hedgehog (2023.1.1) ou supérieur
- JDK 17
- Android SDK (API 24 minimum, API 34 cible)
- Appareil/émulateur Android 7.0+

#### Étapes Rapides
1. Ouvrir le projet dans Android Studio
2. Sync Gradle files
3. Build > Make Project
4. Run sur appareil/émulateur

#### Premier Lancement
L'application démarre avec une base vide. Deux options :
1. **Ajouter manuellement** vos bouteilles via le bouton +
2. **Utiliser les données d'exemple** (voir SampleDataGenerator.kt)

### 🎨 Interface Utilisateur

**Design**
- Material Design 3
- Palette de couleurs thème vin (bordeaux et or)
- Mode clair/sombre supporté
- Interface intuitive et moderne

**Navigation**
- Navigation Compose fluide
- Transitions animées
- Retour arrière intuitif

**Écrans**
1. **Liste des bouteilles** - Vue principale avec recherche et filtres
2. **Détails** - Informations complètes et dégustations
3. **Ajout/Édition** - Formulaire complet avec validation
4. **Statistiques** - Dashboard complet
5. **Dégustation** - Ajout de notes de dégustation

### 💾 Stockage des Données

**Base de données locale**
- SQLite via Room
- Toutes les données stockées en local
- Pas de connexion internet requise
- Backup Android automatique

**Tables**
- `bottles` - Informations des bouteilles
- `tastings` - Historique des dégustations

**Relations**
- Foreign Key : tastings → bottles
- Cascade delete : suppression d'une bouteille supprime ses dégustations

### 📱 Compatibilité

- **Minimum** : Android 7.0 (API 24)
- **Cible** : Android 14 (API 34)
- **Testé sur** : Émulateurs Android Studio

### 🔒 Permissions

L'application demande les permissions suivantes :
- `CAMERA` (optionnel) - Pour futures fonctionnalités photo
- `READ_MEDIA_IMAGES` (optionnel) - Pour futures fonctionnalités photo

Note : Dans la version actuelle, aucune permission n'est réellement utilisée.

### 📈 Évolutions Possibles

**Fonctionnalités Futures**
- [ ] Capture photo des étiquettes
- [ ] Scan code-barres/QR
- [ ] Export CSV/PDF
- [ ] Synchronisation cloud
- [ ] Widget Android
- [ ] Alertes consommation
- [ ] Graphiques avancés
- [ ] Localisation multilingue

### 🎯 Points Forts du Projet

1. **Architecture propre** - MVVM avec séparation des responsabilités
2. **Code moderne** - Kotlin idiomatique, Compose, Coroutines
3. **UI responsive** - Material Design 3, animations fluides
4. **Base solide** - Room database avec migrations
5. **Prêt pour production** - Structure scalable et maintenable
6. **Sans dépendances cloud** - Fonctionne 100% offline
7. **Documentation complète** - Code commenté + guides utilisateur

### 🛠️ Dépendances Clés

```kotlin
// Jetpack Compose
androidx.compose.ui:ui
androidx.compose.material3:material3

// Room Database
androidx.room:room-runtime:2.6.0
androidx.room:room-ktx:2.6.0

// Navigation
androidx.navigation:navigation-compose:2.7.5

// Lifecycle & ViewModel
androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2
```

### 📊 Statistiques du Projet

- **Fichiers Kotlin** : 15+
- **Fichiers XML** : 7
- **Lignes de code** : ~2500
- **Écrans** : 5 principaux
- **Composables** : 15+
- **Fonctions DAO** : 20+

### ✅ Checklist Fonctionnelle

**Gestion Bouteilles**
- [x] Ajouter une bouteille
- [x] Modifier une bouteille
- [x] Supprimer une bouteille
- [x] Afficher la liste
- [x] Voir les détails
- [x] Gérer les quantités

**Recherche & Filtres**
- [x] Recherche textuelle
- [x] Filtres par type
- [x] Combinaison filtres

**Dégustations**
- [x] Ajouter une dégustation
- [x] Noter sur 5 étoiles
- [x] Notes détaillées
- [x] Historique

**Statistiques**
- [x] Total bouteilles
- [x] Valeur totale
- [x] Prix moyen
- [x] Répartition types
- [x] Top régions
- [x] Top millésimes

### 🎓 Apprentissages Techniques

Ce projet démontre :
- Maîtrise de Jetpack Compose
- Architecture MVVM complète
- Gestion de base de données Room
- Navigation Compose
- StateFlow et Coroutines
- Material Design 3
- Validation de formulaires
- Gestion d'état complexe

### 📝 Notes Importantes

1. **Pas de cloud** - Toutes les données sont locales
2. **Backup recommandé** - Utilisez les backups Android automatiques
3. **Production ready** - Structure prête pour déploiement
4. **Extensible** - Facile d'ajouter de nouvelles fonctionnalités
5. **Maintenable** - Code propre et bien structuré

### 🔗 Fichiers à Consulter

1. **README.md** - Documentation technique détaillée
2. **GUIDE_UTILISATEUR.md** - Guide utilisateur complet
3. **MainActivity.kt** - Point d'entrée et navigation
4. **WineCellarViewModel.kt** - Logique métier centrale
5. **BottleListScreen.kt** - Exemple d'écran Compose

### 🏁 Prêt à Utiliser

Le projet est **100% fonctionnel** et prêt à être :
- ✅ Compilé dans Android Studio
- ✅ Déployé sur appareil Android
- ✅ Testé avec les données d'exemple
- ✅ Étendu avec de nouvelles fonctionnalités
- ✅ Publié sur le Play Store (après ajout d'icônes/assets)

---

**Développé avec passion pour les amateurs de vin ! 🍷**

*Un projet complet, moderne et professionnel pour gérer votre cave à vin sur Android.*
