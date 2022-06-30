package uz.gita.bank.presentation.viewModels.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.gita.bank.R
import uz.gita.bank.presentation.viewModels.AccountRecoveryVM
import javax.inject.Inject

@HiltViewModel
class AccountRecoveryVMIpl @Inject constructor(

) : AccountRecoveryVM,ViewModel() {
    override val goToNextScreenLiveData = MutableLiveData<Unit>()
    override val wrongPhoneLiveData =  MutableLiveData<Int>()
    override val popBackStackLiveData = MutableLiveData<Unit>()

    override fun popBackStack() {
        popBackStackLiveData.value = Unit
    }

    override fun checkPhone(phone: String) {
        val a = phone.replace(" ","")
        if (!("\\+998[0-9]*".toRegex().matches(a) && a.length == 13)){
            wrongPhoneLiveData.value = R.string.wrong_phone
        }else{
            wrongPhoneLiveData.value = -1
        }
    }

    override fun goToVerifyScreen() {
        goToNextScreenLiveData.value = Unit
    }
}