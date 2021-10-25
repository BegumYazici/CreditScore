# CreditScore
The ProgressBar developed using MVVM architecture on Kotlin

## Libraries Used
- **Foundation** 🎯
1. AppCompat
2. Android KTX
3. Test
- **Architecture** 🛠
1. View Binding
2. Lifecycles
3. LiveData
4. ViewModel
- **UI** 🔮
1. Fragment
2. Layout
- **Third party** 🖇
1. Retrofit for fetching data
2. Kotlin Coroutines for managing background threads with simplified code and reducing needs for callbacks
3. Dagger Hilt for dependency injection

## Tests 🔍
I've tested these cases in my project;
1. Does credit score api service call when loadCreditScore() function call?
2. Is the status be done when credit score api service return success?
3. Is the status be done when credit score api service return error?
4. When loadCreditScore() is called progress value should be calculate correct?


## Screenshots 📸
<img align="left" src="./art/success_state_screen.png" width="300" height="500">
<p align="center">
<img src="./art/loading_state_screen.png" width="300" height="500">
</p>
<img align="left" src="./art/error_state_screen.png" width="300" height="500">
