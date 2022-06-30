package uz.gita.bank.domain.usecase.impl

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.gita.bank.data.locate.MessageData
import uz.gita.bank.data.locate.ResultData
import uz.gita.bank.data.locate.models.VerificationData
import uz.gita.bank.domain.repository.AppRepository
import javax.inject.Inject

class VerifyUseCase @Inject constructor(
   private val repository: AppRepository
) {
     fun verify(data: VerificationData) = flow<ResultData<Unit>> {
         Log.d("VVV","UC")
           emit(repository.verification(data))
    }
        .catch { ResultData.Fail(message = MessageData.Text("Error : $it")) }
        .flowOn(Dispatchers.IO)
}