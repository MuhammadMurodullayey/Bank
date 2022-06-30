package uz.gita.bank.presentation.ui.screens

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.bank.R
import uz.gita.bank.databinding.ScreenAccountRecoveryBinding
import uz.gita.bank.presentation.viewModels.AccountRecoveryVM
import uz.gita.bank.presentation.viewModels.impl.AccountRecoveryVMIpl

@AndroidEntryPoint
class AccountRecoveryScreen : Fragment(R.layout.screen_account_recovery) {
    private val viewModel : AccountRecoveryVM by viewModels<AccountRecoveryVMIpl>()
    private val binding by viewBinding(ScreenAccountRecoveryBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val phone = arguments?.getString("PHONE") as String
        binding.editPhoneNumber.setText(phone)
        viewModel.checkPhone(phone)
        binding.btnSend.setOnClickListener {
            viewModel.goToVerifyScreen()
        }
        binding.editPhoneNumber.addTextChangedListener {
            viewModel.checkPhone(it.toString())
        }
        viewModel.wrongPhoneLiveData.observe(viewLifecycleOwner,wrongObserver)
        viewModel.goToNextScreenLiveData.observe(viewLifecycleOwner,goToNextScreenObserver)
    }
    private val wrongObserver = Observer<Int>{
       if (it != -1){
           binding.wrongPhoneText.visibility = View.VISIBLE
           binding.wrongPhoneText.text = resources.getString(it)
           binding.editPhoneNumber.setBackgroundResource(R.drawable.bg_error)
           binding.btnSend.isEnabled = false
           binding.btnSend.setTextColor(resources.getColor(R.color.color_un_enable))
       }else{
           binding.wrongPhoneText.visibility = View.GONE
           binding.editPhoneNumber.setBackgroundResource(R.drawable.rectangle_4)
           binding.btnSend.isEnabled = true
           binding.btnSend.setTextColor(resources.getColor(R.color.white))

       }
    }
    private val goToNextScreenObserver = Observer<Unit>{
        val bundle = Bundle()
        bundle.putString("NAVIGATION","ACCOUNTRECOVERY")
     findNavController().navigate(R.id.action_accountRecoveryScreen_to_verificationScreen,bundle)
    }

}