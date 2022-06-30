package uz.gita.bank.presentation.viewModels

import androidx.lifecycle.LiveData

interface AccountRecoveryVM {
    val goToNextScreenLiveData : LiveData<Unit>
    val wrongPhoneLiveData : LiveData<Int>
    val popBackStackLiveData : LiveData<Unit>
    fun popBackStack()
    fun checkPhone(phone : String)
    fun goToVerifyScreen()
}