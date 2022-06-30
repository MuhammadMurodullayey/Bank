package uz.gita.bank.data.locate.models

import java.io.Serializable

data class SignUpData(
    val firstName: String,
    val lastName: String,
    val password: String,
    val phone: String
) : Serializable
