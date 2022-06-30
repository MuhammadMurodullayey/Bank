package uz.gita.bank.presentation.ui.screens

import `in`.aabhasjindal.otptextview.OTPListener
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.bank.R
import uz.gita.bank.data.locate.models.VerificationData
import uz.gita.bank.databinding.ScreenVerificationBinding
import uz.gita.bank.presentation.viewModels.VerificationVM
import uz.gita.bank.presentation.viewModels.impl.VerificationVMImpl
@AndroidEntryPoint
class VerificationScreen :Fragment(R.layout.screen_verification) {

    private val viewModel: VerificationVM by viewModels<VerificationVMImpl>()
    private val binding by viewBinding(ScreenVerificationBinding::bind)
    private var data = "REGISTER"
    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
   if (arguments?.getString("NAVIGATION") != null) {
       data = arguments?.getString("NAVIGATION") as String
   }

        viewModel.startTime()
        viewModel.goToNextScreenLiveData.observe(this@VerificationScreen,goToNextScreenObserver)
        viewModel.takeTimeLiveData.observe(viewLifecycleOwner,takeTimeObserver)
        viewModel.errorLiveData2.observe(viewLifecycleOwner,error2Observer)
        viewModel.errorLiveData.observe(viewLifecycleOwner,errorObserver)
        viewModel.progressLiveData.observe(viewLifecycleOwner,progressObserver)
        binding.btnVerify.setOnClickListener {
            if (data == "REGISTER"){
            viewModel.verify(VerificationData(binding.otpView.otp.toString()))
            }else{
               viewModel.goToPasswordChangeScreen()
            }
        }
   binding.otpView.otpListener = object : OTPListener{
       override fun onInteractionListener() {
           binding.btnVerify.isEnabled = false
           binding.btnVerify.setTextColor(resources.getColor(R.color.color_un_enable))
       }

       override fun onOTPComplete(otp: String) {
         binding.btnVerify.isEnabled = true
           binding.btnVerify.setTextColor(resources.getColor(R.color.white))
       }

   }
    }
    private val error2Observer = Observer<Int> {
        Toast.makeText(requireContext(), resources.getString(it), Toast.LENGTH_SHORT).show()
    }
    private val errorObserver = Observer<String>{
        Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
    }

    @SuppressLint("SetTextI18n")
    private val takeTimeObserver = Observer<String>{
        binding.time.text = "${resources.getString(R.string.request_in_)} $it"
    }
    private val goToNextScreenObserver = Observer<Int>{
        if (it == 0){
        findNavController().navigate(R.id.action_verificationScreen_to_pinCodeScreen)
        }
    }
    private val progressObserver  =Observer<Boolean>{
        if (it) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}