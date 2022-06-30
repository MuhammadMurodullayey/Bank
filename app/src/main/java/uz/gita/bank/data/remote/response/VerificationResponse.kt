package uz.gita.bank.data.remote.response

data class VerificationResponse(
    val accessToken: String,
    val refreshToken: String
)