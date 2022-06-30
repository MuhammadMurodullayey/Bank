package uz.gita.bank.presentation.viewModels

import androidx.lifecycle.LiveData

interface LanguageVM {
    val goToNextScreenLiveData : LiveData<Unit>

    fun goToNextScreen()
}