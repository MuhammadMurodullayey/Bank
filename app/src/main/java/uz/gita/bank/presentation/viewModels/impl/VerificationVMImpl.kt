package uz.gita.bank.presentation.viewModels.impl

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.gita.bank.data.locate.models.VerificationData
import uz.gita.bank.data.locate.onFail
import uz.gita.bank.data.locate.onResource
import uz.gita.bank.data.locate.onSuccess
import uz.gita.bank.data.locate.onText
import uz.gita.bank.domain.usecase.impl.VerifyUseCase
import uz.gita.bank.presentation.viewModels.VerificationVM
import javax.inject.Inject

@HiltViewModel
class VerificationVMImpl @Inject constructor(
   private val useCase: VerifyUseCase
) : ViewModel(), VerificationVM {

    lateinit var job: Job

    override val goToNextScreenLiveData = MutableLiveData<Int>()
    override val takeTimeLiveData = MutableLiveData<String>()
    override val errorLiveData2 =  MutableLiveData<Int>()
    override val errorLiveData =  MutableLiveData<String>()
    override val progressLiveData = MutableLiveData<Boolean>()
    override fun goToPasswordChangeScreen() {
        goToNextScreenLiveData.value = 1
    }

    override fun verify(data: VerificationData) {
        progressLiveData.value= true
        Log.d("VVV","VM")
        useCase.verify(data).onEach {
            progressLiveData.value = false
            it.onSuccess {
                goToNextScreenLiveData.value = 0
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

    override fun startTime() {
        var time = 59
        if (::job.isInitialized) {
            job.cancel()
        }
        job = viewModelScope.launch(Dispatchers.IO) {
            while (time >= 0) {
                delay(1000)
                takeTimeLiveData.postValue(timeFormat(time))
                time--
            }
        }
    }

    private fun timeFormat(seconds: Int): String {
        val minute = seconds / 60
        val second = seconds % 60
        var answer = ""
        answer = "0$minute:" + (if (second < 10) "0$second" else "$second")
        return answer
    }


    override fun goToNextScreen() {
        goToNextScreenLiveData.value = 0
    }
}