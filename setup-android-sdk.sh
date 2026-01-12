#!/bin/bash

echo "Setting up Android SDK command-line tools..."

# Create SDK directory
mkdir -p $HOME/android-sdk/cmdline-tools
cd $HOME/android-sdk/cmdline-tools

# Download command-line tools
echo "Downloading Android command-line tools..."
curl -o commandlinetools.zip https://dl.google.com/android/repository/commandlinetools-mac-9477386_latest.zip

# Extract
echo "Extracting..."
unzip -q commandlinetools.zip
mv cmdline-tools latest

# Set environment
export ANDROID_HOME=$HOME/android-sdk
export PATH=$PATH:$ANDROID_HOME/cmdline-tools/latest/bin:$ANDROID_HOME/platform-tools

# Accept licenses and install required packages
echo "Installing SDK packages..."
yes | sdkmanager --licenses
sdkmanager "platform-tools" "platforms;android-34" "build-tools;34.0.0"

echo "Android SDK setup complete!"
echo "ANDROID_HOME=$ANDROID_HOME"
