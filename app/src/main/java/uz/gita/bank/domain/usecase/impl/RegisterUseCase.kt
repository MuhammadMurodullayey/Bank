package uz.gita.bank.domain.usecase.impl

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.gita.bank.data.locate.MessageData
import uz.gita.bank.data.locate.ResultData
import uz.gita.bank.data.locate.models.SignUpData
import uz.gita.bank.domain.repository.AppRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val repository: AppRepository
) {
    fun register(data: SignUpData) = flow<ResultData<Unit>> {
        Log.d("TTT", "USECASE")
        emit(repository.signUp(data))
    }
        .catch { ResultData.Fail(message = MessageData.Text("Error: $it")) }
        .flowOn(Dispatchers.IO)

}