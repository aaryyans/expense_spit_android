#!/bin/bash

echo "=========================================="
echo "Upload to GitHub & Build APK Automatically"
echo "=========================================="
echo ""

# Check if git is installed
if ! command -v git &> /dev/null; then
    echo "❌ Git is not installed"
    echo "Install git: https://git-scm.com/download/mac"
    exit 1
fi

echo "✅ Git is installed"
echo ""

# Initialize git if not already
if [ ! -d .git ]; then
    echo "📦 Initializing git repository..."
    git init
    git add .
    git commit -m "Initial commit - Expense Tracker Android App"
    echo "✅ Git repository initialized"
else
    echo "✅ Git repository already exists"
fi

echo ""
echo "=========================================="
echo "Next Steps:"
echo "=========================================="
echo ""
echo "1. Create GitHub account (if needed): https://github.com/signup"
echo ""
echo "2. Create new repository: https://github.com/new"
echo "   - Name: expense-tracker-android"
echo "   - Public or Private"
echo "   - Don't initialize with README"
echo ""
echo "3. Run these commands (replace YOUR_USERNAME):"
echo ""
echo "   git remote add origin https://github.com/YOUR_USERNAME/expense-tracker-android.git"
echo "   git branch -M main"
echo "   git push -u origin main"
echo ""
echo "4. Go to GitHub → Your repository → Actions tab"
echo "   - Wait for build to complete (~5 minutes)"
echo "   - Click on the workflow run"
echo "   - Download 'expense-tracker-apk' artifact"
echo "   - Extract ZIP to get app-debug.apk"
echo ""
echo "=========================================="
echo ""
echo "📦 Project is ready at: /Users/mansingh54/MKS_A1"
echo "📦 Compressed file: /Users/mansingh54/MKS_A1_project.tar.gz"
echo ""
