package uz.gita.bank.presentation.viewModels

import androidx.lifecycle.LiveData
import uz.gita.bank.data.locate.models.SignUpData
import java.util.*

interface RegisterVM {

    val goToNextScreenLiveData : LiveData<Int>
    val successLiveData : LiveData<Boolean>
    val progressLiveData : LiveData<Boolean>
    val wrongFistNameLiveData : LiveData<Int>
    val errorLiveData : LiveData<String>
    val errorLiveData2 : LiveData<Int>
    val wrongLastNameLiveData : LiveData<Int>
    val wrongPhoneLiveData  : LiveData<Int>
    val wrongPasswordLiveData : LiveData<Int>
    fun checkFistName(str : String)
    fun checkLastName(str : String)
    fun checkNumber(str : String)
    fun checkPassword(str : String)
    fun goToLoginScreen()
    fun goToVerificationScreen()
    fun register(data :SignUpData)

}