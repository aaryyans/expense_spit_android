#!/bin/bash

echo "=========================================="
echo "Expense Tracker - APK Builder"
echo "=========================================="
echo ""

# Check for Android SDK
if [ ! -d "$HOME/Library/Android/sdk" ]; then
    echo "❌ Android SDK not found!"
    echo ""
    echo "Please install Android Studio first:"
    echo "1. Download from: https://developer.android.com/studio"
    echo "2. Install and complete setup wizard"
    echo "3. Run this script again"
    echo ""
    exit 1
fi

echo "✅ Android SDK found"
export ANDROID_HOME=$HOME/Library/Android/sdk
export PATH=$PATH:$ANDROID_HOME/tools:$ANDROID_HOME/platform-tools

# Check for Java
if ! command -v java &> /dev/null; then
    echo "❌ Java not found!"
    echo "Install Java 17: brew install openjdk@17"
    exit 1
fi

echo "✅ Java found: $(java -version 2>&1 | head -1)"
echo ""

# Build APK
echo "🔨 Building APK..."
echo ""

./gradlew assembleDebug

if [ $? -eq 0 ]; then
    echo ""
    echo "=========================================="
    echo "✅ APK Built Successfully!"
    echo "=========================================="
    echo ""
    echo "📱 APK Location:"
    echo "   app/build/outputs/apk/debug/app-debug.apk"
    echo ""
    echo "📦 APK Size:"
    ls -lh app/build/outputs/apk/debug/app-debug.apk | awk '{print "   " $5}'
    echo ""
    echo "📲 To install on phone:"
    echo "   1. Transfer app-debug.apk to your phone"
    echo "   2. Open the file and tap Install"
    echo "   3. Enable 'Install from unknown sources' if needed"
    echo ""
    echo "   OR use ADB:"
    echo "   adb install app/build/outputs/apk/debug/app-debug.apk"
    echo ""
else
    echo ""
    echo "❌ Build failed!"
    echo "Check errors above or open project in Android Studio"
    exit 1
fi
