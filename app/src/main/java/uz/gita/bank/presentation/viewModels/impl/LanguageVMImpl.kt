package uz.gita.bank.presentation.viewModels.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.gita.bank.presentation.viewModels.LanguageVM
import javax.inject.Inject

@HiltViewModel
class LanguageVMImpl @Inject constructor(): ViewModel(),LanguageVM {
    override val goToNextScreenLiveData =  MutableLiveData<Unit>()

    override fun goToNextScreen() {
        goToNextScreenLiveData.value= Unit
    }
}