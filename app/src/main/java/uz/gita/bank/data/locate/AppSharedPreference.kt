package uz.gita.bank.data.locate

import android.content.Context
import uz.gita.bank.utils.SharedPreference
import javax.inject.Inject

class AppSharedPreference @Inject constructor(context: Context) : SharedPreference(context) {
    // Auth
    var tempToken: String by StringPreference("")

    var accessToken: String by StringPreference("")

    var refreshToken: String by StringPreference("")

}