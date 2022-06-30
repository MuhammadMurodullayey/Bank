package uz.gita.bank.presentation.viewModels

import androidx.lifecycle.LiveData

interface LoginVM {
    val goToNextScreenLiveData : LiveData<Int>
    val successLiveData : LiveData<Boolean>
    val errorLiveData : LiveData<String>
    val phoneErrorLiveData : LiveData<Int>
    val passwordErrorLiveData : LiveData<Int>
    fun goToRegisterScreen()
    fun goForChangePassword()
    fun checkPhoneNumber(phone:String)
    fun checkPassword(password:String)
}