package uz.gita.bank.presentation.ui.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.bank.R
import uz.gita.bank.databinding.ScreenPincodeBinding
import uz.gita.bank.presentation.viewModels.PinCodeVM
import uz.gita.bank.presentation.viewModels.impl.PinCodeVMImpl
@AndroidEntryPoint
class PinCodeScreen : Fragment(R.layout.screen_pincode) {

    private val viewModel : PinCodeVM by viewModels<PinCodeVMImpl>()
    private val binding by viewBinding(ScreenPincodeBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         binding.view.setOnClickListener {

         }

        binding.buttonClear.setOnClickListener {
            if (binding.otpView.otp?.length!! > 0){
                binding.otpView.setOTP(binding.otpView.otp.toString().substring(0, binding.otpView.otp!!.length - 1))
            }
        }
        binding.buttonOne.setOnClickListener {
            binding.otpView.setOTP(binding.otpView.otp.toString() + "1")
        }
        binding.buttonTwo.setOnClickListener {
            binding.otpView.setOTP(binding.otpView.otp.toString() + "2")
        }
        binding.buttonThree.setOnClickListener {
            binding.otpView.setOTP(binding.otpView.otp.toString() + "3")
        }
        binding.buttonFour.setOnClickListener {
            binding.otpView.setOTP(binding.otpView.otp.toString() + "4")
        }
        binding.buttonFive.setOnClickListener {
            binding.otpView.setOTP(binding.otpView.otp.toString() + "5")
        }
        binding.buttonSix.setOnClickListener {
            binding.otpView.setOTP(binding.otpView.otp.toString() + "6")
        }
        binding.buttonSeven.setOnClickListener {
            binding.otpView.setOTP(binding.otpView.otp.toString() + "7")
        }
        binding.buttonEight.setOnClickListener {
            binding.otpView.setOTP(binding.otpView.otp.toString() + "8")
        }
        binding.buttonNine.setOnClickListener {
            binding.otpView.setOTP(binding.otpView.otp.toString() + "9")
        }
        binding.buttonZero.setOnClickListener {
            binding.otpView.setOTP(binding.otpView.otp.toString() + "0")
        }
    }
}