package uz.gita.bank.presentation.ui.screens

import android.annotation.SuppressLint
import android.os.Build.VERSION_CODES.O
import android.os.Bundle
import android.telephony.PhoneNumberFormattingTextWatcher
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.bank.R
import uz.gita.bank.databinding.ScreenSignInBinding
import uz.gita.bank.presentation.viewModels.LoginVM
import uz.gita.bank.presentation.viewModels.impl.LoginVMImpl
import java.util.*

@AndroidEntryPoint
class LoginScreen : Fragment(R.layout.screen_sign_in) {
    private val viewModel: LoginVM by viewModels<LoginVMImpl>()
    private val binding by viewBinding(ScreenSignInBinding::bind)

    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.editPhoneNumber.addTextChangedListener( PhoneNumberFormattingTextWatcher())
        binding.forgotPassword.setOnClickListener {
            viewModel.goForChangePassword()
        }
        binding.editPhoneNumber.addTextChangedListener {
           viewModel.checkPhoneNumber(it.toString())
        }
        binding.editorPassword.addTextChangedListener {
           viewModel.checkPassword(it.toString())
        }
        viewModel.goToNextScreenLiveData.observe(this@LoginScreen,goToNextScreenObserver)
        viewModel.successLiveData.observe(viewLifecycleOwner,successObserver)
        viewModel.phoneErrorLiveData.observe(viewLifecycleOwner,phoneErrorObserver)
        viewModel.passwordErrorLiveData.observe(viewLifecycleOwner,passwordErrorObserver)

        binding.btnLogin.setOnClickListener {
            viewModel.goToRegisterScreen()
        }
    }
    private val phoneErrorObserver = Observer<Int>{
        if (it != -1){
        binding.wrongPhoneText.text = resources.getString(it)
        binding.wrongPhoneText.visibility = View.VISIBLE
        binding.editPhoneNumber.setBackgroundResource(R.drawable.bg_error)
        }else{
            binding.wrongPhoneText.visibility  = View.GONE
            binding.editPhoneNumber.setBackgroundResource(R.drawable.rectangle_4)
        }

    }
    private val passwordErrorObserver = Observer<Int>{
        if (it != -1){
            binding.wrongPasswordText.text = resources.getString(it)
            binding.wrongPasswordText.visibility = View.VISIBLE
            binding.editorPassword.setBackgroundResource(R.drawable.bg_error)
        }else{
            binding.wrongPasswordText.visibility  = View.GONE
            binding.editorPassword.setBackgroundResource(R.drawable.rectangle_4)
        }

    }
    private val successObserver = Observer<Boolean>{
        binding.btnLogin.isEnabled = it
        if (it){
            with(binding) {
                btnLogin.setTextColor(
                        resources.getColor(R.color.white)
                    )
            }

        }else{
            with(binding) {
                btnLogin.setTextColor(
                        resources.getColor(R.color.color_un_enable)
                    )
            }
        }
    }
    private val goToNextScreenObserver = Observer<Int>{
        if (it == 0){
            findNavController().navigate(R.id.action_loginScreen_to_registerScreen)
        }else{
            val phone = Bundle()
            phone.putString("PHONE",binding.editPhoneNumber.text.toString())
          findNavController().navigate(R.id.action_loginScreen_to_accountRecoveryScreen,phone)
        }
    }
}