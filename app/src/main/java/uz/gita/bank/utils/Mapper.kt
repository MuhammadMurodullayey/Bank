package uz.gita.bank.utils

import uz.gita.bank.data.remote.request.RegisterRequest
import uz.gita.bank.data.locate.models.SignUpData
import uz.gita.bank.data.locate.models.VerificationData
import uz.gita.bank.data.remote.request.VerificationRequest
import uz.gita.bank.data.remote.response.VerificationResponse


object Mapper {

    fun SignUpData.toRequest() = RegisterRequest(
        firstName = firstName,
        lastName = lastName,
        phone = phone,
        password = password
    )
    fun VerificationData.toRequest() = VerificationRequest(code = code)

}