# 📱 Build APK - Quick Guide

## ⚡ Fastest Method (Recommended)

### Option 1: Using Android Studio
1. Install Android Studio from https://developer.android.com/studio
2. Open Android Studio → Open → Select `/Users/mansingh54/MKS_A1`
3. Wait for Gradle sync
4. Menu: Build → Build Bundle(s) / APK(s) → Build APK(s)
5. APK location: `app/build/outputs/apk/debug/app-debug.apk`

### Option 2: Using Command Line
```bash
cd /Users/mansingh54/MKS_A1
./build-apk.sh
```

## 📲 Install APK on Phone

1. Transfer `app-debug.apk` to your Android phone
2. Open the file on your phone
3. Tap "Install" (enable unknown sources if asked)
4. Done! Open "Expense Tracker" app

## ⚠️ Requirements

- **Android Studio** (includes Android SDK) - REQUIRED
- **Java JDK 17+** (usually comes with Android Studio)

## 🎯 What You Get

- **App Name**: Expense Tracker
- **APK Size**: ~5-8 MB
- **Min Android**: 7.0 (API 24)
- **Target Android**: 14 (API 34)

## 📝 Files Created

- `build-apk.sh` - Automated build script
- `BUILD_APK_GUIDE.md` - Detailed build instructions
- `README.md` - App documentation
- All source code in `app/src/main/`

---

**Next Step**: Install Android Studio, then run `./build-apk.sh`
