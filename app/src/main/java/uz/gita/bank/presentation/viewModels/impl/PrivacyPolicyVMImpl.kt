package uz.gita.bank.presentation.viewModels.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.gita.bank.presentation.viewModels.PrivacyPolicyVM
import javax.inject.Inject

@HiltViewModel
class PrivacyPolicyVMImpl @Inject constructor(): PrivacyPolicyVM,ViewModel(){
    override val goToNextScreenLiveData =  MutableLiveData<Unit>()

    override fun goToNextScreen() {
        goToNextScreenLiveData.value = Unit
    }
}