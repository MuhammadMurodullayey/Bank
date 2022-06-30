package uz.gita.bank.domain.repository.impl

import android.util.Log
import timber.log.Timber
import uz.gita.bank.R
import uz.gita.bank.data.locate.AppSharedPreference
import uz.gita.bank.data.locate.MessageData
import uz.gita.bank.data.locate.ResultData
import uz.gita.bank.data.locate.models.SignUpData
import uz.gita.bank.data.locate.models.VerificationData
import uz.gita.bank.data.remote.api.Api
import uz.gita.bank.domain.repository.AppRepository
import uz.gita.bank.utils.Mapper.toRequest
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    private val api: Api,
    private val pref: AppSharedPreference

) : AppRepository {

    override suspend fun signUp(data: SignUpData): ResultData<Unit> {
        val request = data.toRequest()
        Timber.tag("TTT").d(request.phone)
        val response = api.register(request)
        return when (response.code()) {
            200 -> {
                val token = response.body()!!.token
                pref.tempToken = token
                ResultData.Success(Unit)
            }
            403 -> {
                val message = response.message()
                ResultData.Fail(MessageData.Text(message))
            }
            else -> {
                ResultData.Fail(MessageData.Resource(R.string.text_signup_error))
            }
        }
    }

    override suspend fun verification(data: VerificationData): ResultData<Unit> {

        val response = api.signUpVerify(token = "Bearer ${pref.tempToken}", request = data.toRequest())
        return when (response.code()) {
            200 -> {
                val tokens = response.body()!!
                pref.tempToken = ""
                pref.accessToken = tokens.accessToken
                pref.refreshToken = tokens.refreshToken
                ResultData.Success(Unit)
            }
            403 -> {
                val message = response.message()
                ResultData.Fail(message = MessageData.Text(message))
            }
            else -> {
                ResultData.Fail(message = MessageData.Resource(R.string.text_verification_error))
            }

        }
    }
}
