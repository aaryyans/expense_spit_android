# GitHub Build Instructions

## Step 1: Create GitHub Repository

1. Go to: https://github.com/new
2. Repository name: `expense-tracker-android`
3. Choose Public or Private
4. **DO NOT** check "Add a README file"
5. Click "Create repository"

## Step 2: Push Your Code

Copy your repository URL from GitHub (looks like: https://github.com/YOUR_USERNAME/expense-tracker-android.git)

Then run these commands:

```bash
cd /Users/mansingh54/MKS_A1

git remote add origin https://github.com/YOUR_USERNAME/expense-tracker-android.git

git branch -M main

git push -u origin main
```

**Note**: Replace `YOUR_USERNAME` with your actual GitHub username

## Step 3: Wait for Build

1. Go to your repository on GitHub
2. Click the "Actions" tab at the top
3. You'll see "Build Android APK" workflow running
4. Wait ~5 minutes for it to complete (green checkmark)

## Step 4: Download APK

1. Click on the completed workflow run
2. Scroll down to "Artifacts" section
3. Click "expense-tracker-apk" to download
4. Extract the ZIP file
5. You'll get `app-debug.apk`

## Step 5: Install on Phone

1. Transfer `app-debug.apk` to your Android phone
2. Open the file
3. Tap "Install"
4. Done!

---

## Troubleshooting

**If git push asks for credentials:**
- Use GitHub Personal Access Token instead of password
- Create token at: https://github.com/settings/tokens
- Or use GitHub Desktop app

**If build fails:**
- Check the Actions tab for error logs
- Usually auto-fixes on retry

---

Ready to push? Run the commands in Step 2!
