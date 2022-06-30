package uz.gita.bank.presentation.viewModels

import androidx.lifecycle.LiveData
import uz.gita.bank.data.locate.models.VerificationData

interface VerificationVM {
    val takeTimeLiveData : LiveData<String>
    val goToNextScreenLiveData : LiveData<Int>
    val errorLiveData2 : LiveData<Int>
    val errorLiveData : LiveData<String>
    val progressLiveData : LiveData<Boolean>

    fun goToNextScreen()
    fun startTime()
    fun goToPasswordChangeScreen()
    fun verify(data : VerificationData)
}