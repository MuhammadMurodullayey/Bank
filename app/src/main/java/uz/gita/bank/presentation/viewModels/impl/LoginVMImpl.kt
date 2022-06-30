package uz.gita.bank.presentation.viewModels.impl

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.gita.bank.R
import uz.gita.bank.presentation.viewModels.LoginVM
import javax.inject.Inject

@HiltViewModel
class LoginVMImpl @Inject constructor(): ViewModel(),LoginVM {
    private var isCorrectPhone = false
    private var isCorrectPassword = false
    override val goToNextScreenLiveData =  MutableLiveData<Int>()
    override val successLiveData = MutableLiveData<Boolean>()

    override val phoneErrorLiveData =  MutableLiveData<Int>()
    override val passwordErrorLiveData =  MutableLiveData<Int>()
    override val errorLiveData =  MutableLiveData<String>()


    override fun goToRegisterScreen() {
        goToNextScreenLiveData.value = 0
    }

    override fun goForChangePassword() {
        goToNextScreenLiveData.value = 1
    }

    override fun checkPhoneNumber(phone: String) {
        val a = phone.replace(" ","")
        isCorrectPhone = "\\+998[0-9]*".toRegex().matches(a) && a.length == 13
        if (!isCorrectPhone){
            phoneErrorLiveData.value = R.string.wrong_phone
        }else{
            phoneErrorLiveData.value = -1
        }
        successLiveData.value = isCorrectPhone && isCorrectPassword
    }

    override fun checkPassword(password: String) {
        isCorrectPassword = password.length >= 6
        if (!isCorrectPassword){
            passwordErrorLiveData.value = R.string.wrong_password
        }else{
            passwordErrorLiveData.value =-1

        }
        successLiveData.value = isCorrectPhone && isCorrectPassword
    }
}