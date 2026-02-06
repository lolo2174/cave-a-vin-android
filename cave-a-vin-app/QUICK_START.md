# 🚀 Démarrage Rapide - Cave à Vin

## ⚡ Installation en 5 Minutes

### 1️⃣ Télécharger et Décompresser
```bash
# Décompressez l'archive cave-a-vin-app.zip
# Vous obtenez le dossier : cave-a-vin-app/
```

### 2️⃣ Ouvrir dans Android Studio
1. Lancez **Android Studio**
2. Cliquez sur **File > Open**
3. Sélectionnez le dossier **cave-a-vin-app**
4. Attendez la synchronisation Gradle (1-2 minutes)

### 3️⃣ Préparer l'Environnement

**Option A : Émulateur (Recommandé pour test)**
1. Tools > Device Manager
2. Create Device
3. Sélectionnez un téléphone (ex: Pixel 6)
4. Téléchargez Android 14 (API 34)
5. Finish

**Option B : Appareil Physique**
1. Activez le mode développeur sur votre Android :
   - Paramètres > À propos du téléphone
   - Appuyez 7 fois sur "Numéro de build"
2. Activez le débogage USB :
   - Paramètres > Options pour les développeurs
   - Activer "Débogage USB"
3. Connectez votre téléphone en USB

### 4️⃣ Compiler et Lancer
1. Cliquez sur le bouton **▶ Run** (ou Shift+F10)
2. Sélectionnez votre appareil/émulateur
3. Attendez l'installation (30 secondes)
4. **L'application se lance automatiquement !**

---

## 📱 Premier Usage

### Ajouter Votre Première Bouteille
1. Appuyez sur le bouton **+** (en bas à droite)
2. Remplissez les champs :
   - **Nom** : Château Margaux
   - **Appellation** : Margaux
   - **Millésime** : 2015
   - **Région** : Bordeaux
   - **Cépage** : Cabernet Sauvignon
   - **Type** : Rouge
   - **Quantité** : 1
   - **Prix** : 150
3. Appuyez sur **💾 Sauvegarder**

### Tester les Fonctionnalités
✅ **Recherche** : Tapez "Margaux" dans la barre de recherche  
✅ **Filtre** : Cliquez sur 🔽 et sélectionnez "Rouge"  
✅ **Détails** : Appuyez sur votre bouteille  
✅ **Statistiques** : Cliquez sur 📊 en haut à droite  
✅ **Dégustation** : Depuis les détails > **+ Ajouter**  

---

## 🎯 Utiliser les Données d'Exemple

Pour tester rapidement avec 10 bouteilles pré-remplies :

### Méthode 1 : Code Direct
1. Ouvrez `MainActivity.kt`
2. Dans `onCreate()`, ajoutez après `setContent` :
```kotlin
// Ajoutez ces lignes
lifecycleScope.launch {
    val generator = SampleDataGenerator(repository)
    generator.generateSampleData()
}
```
3. Importez : `import androidx.lifecycle.lifecycleScope`
4. Re-compilez et lancez

### Méthode 2 : Depuis l'App (TODO)
- Cette fonctionnalité pourrait être ajoutée dans les paramètres

---

## 🛠️ Dépannage Rapide

### ❌ Erreur Gradle Sync
**Problème** : "Failed to sync Gradle project"  
**Solution** :
1. File > Invalidate Caches > Invalidate and Restart
2. Vérifiez votre connexion internet
3. Tools > SDK Manager > SDK Tools > Décochez "Hide Obsolete Packages"

### ❌ APK ne s'installe pas
**Problème** : "Installation failed"  
**Solution** :
1. Désinstallez toute version précédente
2. Build > Clean Project
3. Build > Rebuild Project
4. Relancez

### ❌ Émulateur lent
**Problème** : Émulateur très lent  
**Solution** :
1. Tools > Device Manager
2. Éditez votre AVD
3. Augmentez la RAM à 2048 MB
4. Activez "Hardware - GLES 2.0"

### ❌ Base de données corrompue
**Problème** : Erreur au lancement  
**Solution** :
1. Arrêtez l'app
2. Dans Android Studio : View > Tool Windows > Device File Explorer
3. Naviguez vers : `/data/data/com.example.caveavin/databases/`
4. Supprimez tous les fichiers
5. Relancez l'app

---

## 📊 Versions Recommandées

| Outil | Version Minimum | Version Recommandée |
|-------|----------------|---------------------|
| Android Studio | Hedgehog 2023.1.1 | Hedgehog 2023.1.1+ |
| JDK | 17 | 17 |
| Gradle | 8.2 | 8.2+ |
| Android OS | 7.0 (API 24) | 14.0 (API 34) |

---

## 🎨 Personnalisation Rapide

### Changer les Couleurs
Éditez `app/src/main/java/.../ui/theme/Color.kt` :
```kotlin
val WineRed = Color(0xFF8B0000)  // Votre couleur principale
val WineGold = Color(0xFFDAA520) // Votre couleur secondaire
```

### Changer le Nom de l'App
Éditez `app/src/main/res/values/strings.xml` :
```xml
<string name="app_name">Ma Cave</string>
```

### Ajouter une Icône
1. Right-click sur `res`
2. New > Image Asset
3. Icon Type : Launcher Icons
4. Chargez votre image
5. Next > Finish

---

## 📦 Générer un APK pour Distribution

### Pour Partager l'APK
1. Build > Build Bundle(s) / APK(s) > Build APK(s)
2. Attendez la compilation
3. Cliquez sur "locate" dans la notification
4. L'APK est dans `app/build/outputs/apk/debug/`
5. Partagez `app-debug.apk`

### Pour le Play Store (APK Signé)
1. Build > Generate Signed Bundle / APK
2. Choisissez "Android App Bundle"
3. Créez une nouvelle KeyStore ou utilisez une existante
4. Remplissez les informations
5. Build variant : **release**
6. Finish
7. Le fichier `.aab` est dans `app/release/`

---

## 🔥 Commandes Utiles

### Terminal (dans Android Studio)
```bash
# Nettoyer le projet
./gradlew clean

# Compiler l'app
./gradlew assembleDebug

# Lancer les tests
./gradlew test

# Voir les dépendances
./gradlew dependencies
```

---

## 📚 Ressources

### Documentation
- **README.md** - Documentation technique complète
- **GUIDE_UTILISATEUR.md** - Guide utilisateur détaillé
- **PRESENTATION.md** - Présentation du projet

### Liens Officiels
- [Jetpack Compose](https://developer.android.com/jetpack/compose)
- [Room Database](https://developer.android.com/training/data-storage/room)
- [Material Design 3](https://m3.material.io/)

### Support
- Stack Overflow : [android], [kotlin], [jetpack-compose]
- Documentation Android : developer.android.com

---

## ✅ Checklist de Démarrage

- [ ] Android Studio installé et à jour
- [ ] JDK 17 configuré
- [ ] Projet ouvert et synchronisé
- [ ] Émulateur créé OU appareil connecté
- [ ] App compilée avec succès
- [ ] App lancée et fonctionnelle
- [ ] Première bouteille ajoutée
- [ ] Statistiques consultées

---

## 🎓 Prochaines Étapes

Une fois l'app fonctionnelle :

1. **Explorez le code** 
   - Commencez par `MainActivity.kt`
   - Regardez les screens dans `ui/screens/`
   - Comprenez le ViewModel

2. **Personnalisez**
   - Changez les couleurs du thème
   - Modifiez les textes
   - Ajoutez vos propres fonctionnalités

3. **Étendez**
   - Ajoutez la capture photo
   - Implémentez l'export CSV
   - Créez un widget Android

4. **Partagez**
   - Générez l'APK
   - Testez avec des amis
   - Publiez sur le Play Store

---

## 💡 Astuces Pro

🔥 **Raccourcis Android Studio**
- `Ctrl + N` : Rechercher une classe
- `Ctrl + Shift + N` : Rechercher un fichier
- `Shift + F10` : Lancer l'app
- `Alt + Enter` : Suggestions rapides
- `Ctrl + Alt + L` : Formatter le code

🔥 **Debug Efficace**
- Utilisez `Logcat` pour voir les logs
- Ajoutez des breakpoints (clic gauche sur la marge)
- Utilisez `Log.d("TAG", "message")` dans le code

🔥 **Performance**
- Les émulateurs avec CPU x86 sont plus rapides
- Utilisez "Cold Boot" pour un démarrage propre
- Fermez les apps inutiles pendant le développement

---

**Besoin d'aide ? Consultez le README.md ou le GUIDE_UTILISATEUR.md**

**Bon développement ! 🍷🚀**
