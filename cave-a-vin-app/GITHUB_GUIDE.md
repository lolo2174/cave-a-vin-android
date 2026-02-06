# 🚀 Guide GitHub Actions - Obtenir l'APK automatiquement

## 📋 Étapes à suivre

### 1️⃣ Créer un compte GitHub (si vous n'en avez pas)
1. Allez sur https://github.com
2. Cliquez sur **Sign up**
3. Créez votre compte (gratuit)

### 2️⃣ Créer un nouveau repository
1. Une fois connecté, cliquez sur le **+** en haut à droite
2. Sélectionnez **New repository**
3. Remplissez :
   - **Repository name** : `cave-a-vin-android`
   - **Description** : `Application Android de gestion de cave à vin`
   - Cochez **Public** (pour utiliser GitHub Actions gratuitement)
   - ✅ Cochez **Add a README file**
4. Cliquez sur **Create repository**

### 3️⃣ Uploader le code

**Option A : Via l'interface web (Plus simple)**

1. Sur la page de votre repository, cliquez sur **Add file** > **Upload files**
2. **Décompressez** d'abord le fichier `cave-a-vin-app.zip` sur votre ordinateur
3. **Glissez-déposez** TOUS les fichiers du dossier décompressé dans la zone de GitHub
   - Assurez-vous d'inclure le dossier `.github` (il contient le workflow)
4. En bas, dans "Commit changes":
   - Message : `Initial commit - Cave à Vin app`
5. Cliquez sur **Commit changes**

**Option B : Via Git en ligne de commande (Si vous connaissez Git)**

```bash
# Décompressez cave-a-vin-app.zip
unzip cave-a-vin-app.zip
cd cave-a-vin-app

# Initialisez git
git init
git add .
git commit -m "Initial commit"

# Ajoutez le remote (remplacez USERNAME par votre nom GitHub)
git remote add origin https://github.com/USERNAME/cave-a-vin-android.git
git branch -M main
git push -u origin main
```

### 4️⃣ Lancer la compilation automatique

Une fois le code uploadé, GitHub Actions va **automatiquement** :
1. Détecter le fichier `.github/workflows/build.yml`
2. Lancer la compilation
3. Créer l'APK

**Pour voir la progression :**
1. Allez dans l'onglet **Actions** de votre repository
2. Vous verrez "Build Android APK" en cours d'exécution (⏳ orange)
3. Attendez 5-10 minutes (première fois peut prendre plus longtemps)
4. Ça devient vert ✅ quand c'est terminé

### 5️⃣ Télécharger l'APK

**Méthode 1 : Depuis les Artifacts**
1. Dans **Actions**, cliquez sur le build terminé
2. Descendez jusqu'à **Artifacts**
3. Téléchargez **cave-a-vin-debug-apk.zip**
4. Décompressez-le pour obtenir `app-debug.apk`

**Méthode 2 : Depuis les Releases (si le workflow a créé une release)**
1. Allez dans l'onglet **Releases** (à droite)
2. Cliquez sur la dernière version (ex: v1.0.1)
3. Téléchargez directement **app-debug.apk** (sans zip !)

### 6️⃣ Installer l'APK sur votre téléphone

**Sur votre téléphone Android :**

1. **Transférez l'APK** sur votre téléphone :
   - Par câble USB
   - Par email (envoyez-vous l'APK)
   - Par Google Drive / Dropbox
   - Par WhatsApp / Telegram à vous-même

2. **Autorisez l'installation** :
   - Allez dans **Paramètres** > **Sécurité**
   - Activez **Sources inconnues** ou **Installer des apps inconnues**
   - Autorisez votre navigateur ou gestionnaire de fichiers

3. **Installez** :
   - Ouvrez le fichier `app-debug.apk`
   - Appuyez sur **Installer**
   - Patientez quelques secondes
   - Appuyez sur **Ouvrir** ou trouvez l'app "Cave à Vin" dans vos apps

🎉 **C'est installé !**

---

## 🔄 Mises à jour futures

Si vous modifiez le code et faites un nouveau commit :
1. GitHub Actions recompilera automatiquement
2. Un nouvel APK sera disponible dans **Actions** > **Artifacts**
3. Une nouvelle release sera créée automatiquement

---

## 🛠️ Dépannage

### ❌ Le workflow échoue
**Vérifiez que vous avez bien uploadé :**
- Le dossier `.github/workflows/`
- Le fichier `gradlew` (doit être exécutable)
- Tous les fichiers `build.gradle.kts`
- Le dossier `gradle/wrapper/`

**Solution :** Re-uploadez tous les fichiers depuis le zip décompressé

### ❌ "Actions are disabled"
1. Allez dans **Settings** > **Actions** > **General**
2. Cochez **Allow all actions and reusable workflows**
3. Cliquez sur **Save**
4. Relancez le workflow : **Actions** > **Build Android APK** > **Re-run all jobs**

### ❌ L'APK ne s'installe pas sur mon téléphone
- Vérifiez que votre Android est **version 7.0 ou supérieure**
- Désinstallez toute version précédente
- Vérifiez que l'installation depuis sources inconnues est autorisée

### ❌ Je ne vois pas l'onglet Actions
- Votre repository doit être **Public** pour utiliser Actions gratuitement
- Ou prenez un compte GitHub Pro (gratuit pour les étudiants)

---

## 💡 Astuces

### Compiler manuellement (sans attendre)
Si vous voulez lancer une compilation :
1. **Actions** > **Build Android APK** (dans la liste à gauche)
2. Cliquez sur **Run workflow** (bouton à droite)
3. Sélectionnez `main` branch
4. Cliquez sur **Run workflow**

### Rendre le repository privé
Si vous voulez un repo privé :
- GitHub donne 2000 minutes gratuites de GitHub Actions par mois
- Largement suffisant pour ce projet

### Partager l'APK
Vous pouvez partager le lien de la Release :
```
https://github.com/USERNAME/cave-a-vin-android/releases/latest
```

---

## 📱 Après installation

### Premiers pas
1. Ouvrez l'app "Cave à Vin"
2. Appuyez sur **+** pour ajouter votre première bouteille
3. Consultez les **Statistiques** (icône graphique)
4. Ajoutez des **Dégustations** depuis les détails d'une bouteille

### Données d'exemple
Pour tester rapidement avec 10 bouteilles pré-remplies, vous pouvez modifier le code dans `MainActivity.kt` (voir instructions dans QUICK_START.md)

---

## ✅ Checklist

- [ ] Compte GitHub créé
- [ ] Repository créé (Public)
- [ ] Code uploadé (avec dossier .github)
- [ ] Workflow lancé automatiquement
- [ ] Build réussi (vert ✅)
- [ ] APK téléchargé
- [ ] APK installé sur téléphone
- [ ] App fonctionne !

---

## 🆘 Besoin d'aide ?

Si vous rencontrez un problème :
1. Vérifiez les logs dans **Actions** > votre build > cliquez sur les étapes rouges
2. Consultez le README.md et QUICK_START.md
3. Assurez-vous que tous les fichiers sont bien uploadés

---

**Durée totale estimée : 15-20 minutes** ⏱️

*Une fois configuré, chaque nouveau build prend seulement 5-10 minutes automatiquement !*

🍷 **Bonne installation !**
