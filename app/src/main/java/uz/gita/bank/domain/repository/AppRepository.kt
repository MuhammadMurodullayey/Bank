package uz.gita.bank.domain.repository

import uz.gita.bank.data.locate.ResultData
import uz.gita.bank.data.locate.models.SignUpData
import uz.gita.bank.data.locate.models.VerificationData

interface AppRepository {

    suspend fun signUp(data: SignUpData) : ResultData<Unit>
    suspend fun verification(data : VerificationData) : ResultData<Unit>


}