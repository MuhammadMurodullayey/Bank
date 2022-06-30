package uz.gita.bank.data.remote.api


import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import uz.gita.bank.data.remote.request.RegisterRequest
import uz.gita.bank.data.remote.request.VerificationRequest
import uz.gita.bank.data.remote.response.RegisterResponse
import uz.gita.bank.data.remote.response.VerificationResponse

interface Api {

    @POST("auth/sign-up")
    suspend fun register(@Body registerRequest: RegisterRequest) : Response<RegisterResponse>

    @POST("auth/sign-up/verify")
    suspend fun signUpVerify(@Header("Authorization") token: String, @Body request: VerificationRequest
    ): Response<VerificationResponse>


}