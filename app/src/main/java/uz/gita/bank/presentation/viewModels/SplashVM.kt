package uz.gita.bank.presentation.viewModels

import androidx.lifecycle.LiveData

interface SplashVM {
    val goToNextScreenLiveData : LiveData<Int>

    fun goToLanguageScreen()
    fun goToPinCodeScreen()
}