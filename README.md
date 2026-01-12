# Expense Tracker - Shared Expenses App

A simple Android app to track shared expenses with housemates, friends, and family.

## Features

✅ **Quick Expense Entry** - Only 3 fields: Who paid, For what, How much
✅ **Auto-Split** - Expenses automatically split equally among all members
✅ **Balance Tracking** - See who owes whom at a glance
✅ **Simple UI** - Clean, intuitive interface for fast data entry

## How to Use

1. **Add People** - Tap "Add Person" to add housemates/friends
2. **Add Expenses** - Tap the + button and enter:
   - Who paid it
   - What it was for
   - How much it cost
3. **View Balances** - Tap "View Balances" to see who owes whom

## Setup Instructions

### Prerequisites
- Android Studio (latest version)
- JDK 17 or higher
- Android SDK 24+

### Steps to Run

1. Open Android Studio
2. Select "Open an Existing Project"
3. Navigate to the `MKS_A1` folder
4. Wait for Gradle sync to complete
5. Connect an Android device or start an emulator
6. Click Run (green play button)

## Project Structure

```
app/src/main/java/com/expensetracker/
├── data/
│   ├── database/      # Room database and DAO
│   ├── models/        # Data models (Person, Expense, Balance)
│   └── repository/    # Data operations and balance calculations
├── ui/
│   └── home/          # Main activity and adapter
└── viewmodel/         # ViewModel for UI data management
```

## How It Works

1. **Add People**: Create a list of people sharing expenses
2. **Log Expenses**: When someone pays, log it with 3 simple fields
3. **Auto-Calculate**: App automatically splits expenses equally
4. **View Balances**: See simplified balances showing who owes whom

## Technical Details

- **Language**: Kotlin
- **Architecture**: MVVM
- **Database**: Room (SQLite)
- **UI**: XML layouts with ViewBinding
- **Async**: Coroutines

## Balance Calculation

The app calculates balances by:
1. Splitting each expense equally among all members
2. Tracking what each person paid vs. their share
3. Computing net balances (who owes whom)
4. Simplifying debts to minimize transactions

## Future Enhancements

- Custom split ratios
- Multiple groups
- Expense categories
- Cloud sync
- Export to CSV
- Receipt photos
- Payment reminders

## License

Free to use and modify.
