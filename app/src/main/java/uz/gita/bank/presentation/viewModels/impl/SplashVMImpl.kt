package uz.gita.bank.presentation.viewModels.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.gita.bank.presentation.viewModels.SplashVM
import javax.inject.Inject

@HiltViewModel
class SplashVMImpl @Inject constructor(

): SplashVM,ViewModel() {
    override val goToNextScreenLiveData =  MutableLiveData<Int>()

    override fun goToLanguageScreen() {
        viewModelScope.launch(Dispatchers.IO) {
            delay(2000)
            goToNextScreenLiveData.postValue(0)
        }
    }

    override fun goToPinCodeScreen() {
        viewModelScope.launch(Dispatchers.IO) {
            delay(2000)
            goToNextScreenLiveData.postValue(1)
        }
    }
}