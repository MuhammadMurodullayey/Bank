package uz.gita.bank.presentation.viewModels.impl

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.bank.R
import uz.gita.bank.data.locate.models.SignUpData
import uz.gita.bank.data.locate.onFail
import uz.gita.bank.data.locate.onResource
import uz.gita.bank.data.locate.onSuccess
import uz.gita.bank.data.locate.onText
import uz.gita.bank.domain.usecase.impl.RegisterUseCase
import uz.gita.bank.presentation.viewModels.RegisterVM
import javax.inject.Inject

@HiltViewModel
class RegisterVMImpl @Inject constructor(
    private val useCase: RegisterUseCase
) : ViewModel(), RegisterVM {
    override val goToNextScreenLiveData = MutableLiveData<Int>()
    override val successLiveData = MutableLiveData<Boolean>()
    override val progressLiveData = MutableLiveData<Boolean>()
    override val wrongFistNameLiveData = MutableLiveData<Int>()
    override val wrongLastNameLiveData = MutableLiveData<Int>()
    override val wrongPhoneLiveData = MutableLiveData<Int>()
    override val wrongPasswordLiveData = MutableLiveData<Int>()
    override val errorLiveData =  MutableLiveData<String>()
    override val errorLiveData2 =  MutableLiveData<Int>()
    private var isNumberCorrect = false
    private var isFistNameCorrect = false
    private var isLastNameCorrect = false
    private var isPasswordCorrect = false


    override fun checkFistName(str: String) {
        isFistNameCorrect = str.length > 2
        if (!isFistNameCorrect) {
            wrongFistNameLiveData.value = R.string.wrong_firstname
        } else {
            wrongFistNameLiveData.value = -1
        }
        successLiveData.value = isFistNameCorrect && isLastNameCorrect
                && isNumberCorrect && isPasswordCorrect
    }

    override fun checkLastName(str: String) {
        isLastNameCorrect = str.length > 2
        if (!isLastNameCorrect) {
            wrongLastNameLiveData.value = R.string.wrong_lastname
        } else {
            wrongLastNameLiveData.value = -1
        }
        successLiveData.value = isFistNameCorrect && isLastNameCorrect
                && isNumberCorrect && isPasswordCorrect
    }

    override fun checkNumber(str: String) {
        val a = str.replace(" ", "")
        isNumberCorrect = "\\+998[0-9]*".toRegex().matches(a) && a.length == 13
        if (!isNumberCorrect) {
            wrongPhoneLiveData.value = R.string.wrong_phone
        } else {
            wrongPhoneLiveData.value = -1
        }
        successLiveData.value = isFistNameCorrect && isLastNameCorrect
                && isNumberCorrect && isPasswordCorrect
    }

    override fun checkPassword(str: String) {
        isPasswordCorrect = str.length >= 6
        if (!isPasswordCorrect) {
            wrongPasswordLiveData.value = R.string.wrong_password
        } else {
            wrongPasswordLiveData.value = -1
        }
        successLiveData.value = isFistNameCorrect && isLastNameCorrect
                && isNumberCorrect && isPasswordCorrect
    }

    override fun goToLoginScreen() {
        goToNextScreenLiveData.value = 0
    }

    override fun goToVerificationScreen() {
        goToNextScreenLiveData.value = 1
    }

    override fun register(data: SignUpData) {
        progressLiveData.value = true
        Log.d("TTT","VM")

        useCase.register(data).onEach {
            progressLiveData.value = false
            it.onSuccess {
                goToNextScreenLiveData.value = 1
            }
            it.onFail {
             errorLiveData.value = this.message.toString()
            }
            it.onResource {
                errorLiveData2.value = this.resourceId
            }
            it.onText {
               errorLiveData.value = this.message
            }
        }.launchIn(viewModelScope)
    }
}