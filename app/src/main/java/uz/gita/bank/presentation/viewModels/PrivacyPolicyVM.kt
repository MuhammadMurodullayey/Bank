package uz.gita.bank.presentation.viewModels

import androidx.lifecycle.LiveData

interface PrivacyPolicyVM {
    val goToNextScreenLiveData : LiveData<Unit>
    fun goToNextScreen()
}