# How to Build APK - Expense Tracker

## Prerequisites

You need Android Studio installed on your Mac. If not installed:
1. Download from: https://developer.android.com/studio
2. Install and open Android Studio
3. Complete the setup wizard (it will install Android SDK)

## Method 1: Build APK using Android Studio (RECOMMENDED)

### Steps:

1. **Open Project**
   - Launch Android Studio
   - Click "Open" 
   - Navigate to `/Users/mansingh54/MKS_A1`
   - Click "OK"

2. **Wait for Gradle Sync**
   - Android Studio will automatically sync Gradle
   - Wait for "Gradle sync finished" message
   - This may take 5-10 minutes on first run

3. **Build APK**
   - Click `Build` menu → `Build Bundle(s) / APK(s)` → `Build APK(s)`
   - Wait for build to complete
   - You'll see "APK(s) generated successfully" notification

4. **Locate APK**
   - Click "locate" in the notification, OR
   - Find it at: `app/build/outputs/apk/debug/app-debug.apk`

5. **Install on Phone**
   - Transfer `app-debug.apk` to your Android phone
   - Open the file on your phone
   - Allow "Install from unknown sources" if prompted
   - Tap "Install"

## Method 2: Build APK using Command Line

### Requirements:
- Android Studio must be installed (for Android SDK)
- Java JDK 17+ installed

### Steps:

1. **Open Terminal**
   ```bash
   cd /Users/mansingh54/MKS_A1
   ```

2. **Build Debug APK**
   ```bash
   ./gradlew assembleDebug
   ```

3. **Find APK**
   - Location: `app/build/outputs/apk/debug/app-debug.apk`

4. **Build Release APK (Unsigned)**
   ```bash
   ./gradlew assembleRelease
   ```
   - Location: `app/build/outputs/apk/release/app-release-unsigned.apk`

## Method 3: Generate Signed Release APK (For Distribution)

### Create Keystore:
```bash
keytool -genkey -v -keystore expense-tracker.keystore -alias expense_key -keyalg RSA -keysize 2048 -validity 10000
```

### Add to app/build.gradle:
```gradle
android {
    signingConfigs {
        release {
            storeFile file("../expense-tracker.keystore")
            storePassword "your_password"
            keyAlias "expense_key"
            keyPassword "your_password"
        }
    }
    buildTypes {
        release {
            signingConfig signingConfigs.release
            minifyEnabled false
        }
    }
}
```

### Build Signed APK:
```bash
./gradlew assembleRelease
```

## Troubleshooting

### "Android SDK not found"
- Install Android Studio
- Or set ANDROID_HOME environment variable:
  ```bash
  export ANDROID_HOME=$HOME/Library/Android/sdk
  export PATH=$PATH:$ANDROID_HOME/tools:$ANDROID_HOME/platform-tools
  ```

### "Java not found"
- Install JDK 17:
  ```bash
  brew install openjdk@17
  ```

### "Gradle sync failed"
- Open project in Android Studio
- Click "File" → "Sync Project with Gradle Files"

## Quick Start (If Android Studio is Installed)

```bash
cd /Users/mansingh54/MKS_A1
./gradlew assembleDebug
```

APK will be at: `app/build/outputs/apk/debug/app-debug.apk`

## Transfer APK to Phone

### Via USB:
```bash
adb install app/build/outputs/apk/debug/app-debug.apk
```

### Via Cloud:
- Upload to Google Drive/Dropbox
- Download on phone
- Install

### Via Email:
- Email APK to yourself
- Open on phone
- Install

## APK Types

- **Debug APK**: For testing, larger size, includes debug info
- **Release APK**: Optimized, smaller, for distribution
- **Signed APK**: Required for Play Store or secure distribution

## File Sizes (Approximate)

- Debug APK: ~5-8 MB
- Release APK: ~3-5 MB

---

**Note**: The debug APK is perfect for personal use and testing with friends/family. For Play Store distribution, you need a signed release APK.
