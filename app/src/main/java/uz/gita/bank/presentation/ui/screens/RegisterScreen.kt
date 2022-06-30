package uz.gita.bank.presentation.ui.screens

import android.annotation.SuppressLint
import android.os.Build.VERSION_CODES.P
import android.os.Bundle
import android.telephony.PhoneNumberFormattingTextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.bank.R
import uz.gita.bank.data.locate.models.SignUpData
import uz.gita.bank.databinding.ScreenSignUpBinding
import uz.gita.bank.presentation.viewModels.RegisterVM
import uz.gita.bank.presentation.viewModels.impl.RegisterVMImpl

@AndroidEntryPoint
class RegisterScreen : Fragment(R.layout.screen_sign_up) {

    private val viewModel: RegisterVM by viewModels<RegisterVMImpl>()
    private val binding by viewBinding(ScreenSignUpBinding::bind)

    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.editPhoneNumber.addTextChangedListener(PhoneNumberFormattingTextWatcher())

        binding.btnRegister.setOnClickListener {
            val phone = binding.editPhoneNumber.text.toString().replace(" ", "").substring(4,13)

            viewModel.register(
                SignUpData(
                    binding.editFistName.text.toString(),
                    binding.editLastName.text.toString(),
                    binding.editor1Password.text.toString(),
                    phone
                )
            )
        }


        viewModel.goToNextScreenLiveData.observe(this@RegisterScreen, goToNextScreenObserver)
        viewModel.successLiveData.observe(viewLifecycleOwner, successObserver)
        viewModel.wrongFistNameLiveData.observe(viewLifecycleOwner, wrongFistNameObserver)
        viewModel.wrongLastNameLiveData.observe(viewLifecycleOwner, wrongLastNameObserver)
        viewModel.wrongPasswordLiveData.observe(viewLifecycleOwner, wrongPasswordObserver)
        viewModel.wrongPhoneLiveData.observe(viewLifecycleOwner, wrongPhoneObserver)
        viewModel.errorLiveData.observe(viewLifecycleOwner, errorObserver)
        viewModel.errorLiveData2.observe(viewLifecycleOwner, errorIntObserver)
        viewModel.progressLiveData.observe(viewLifecycleOwner, progressObserver)

        binding.editFistName.addTextChangedListener { viewModel.checkFistName(it.toString()) }
        binding.editLastName.addTextChangedListener { viewModel.checkLastName(it.toString()) }
        binding.editPhoneNumber.addTextChangedListener { viewModel.checkNumber(it.toString()) }
        binding.editor1Password.addTextChangedListener { viewModel.checkPassword(it.toString()) }


    }

    private val progressObserver = Observer<Boolean> {
        if (it) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
    private val errorObserver = Observer<String> {
        Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
    }
    private val errorIntObserver = Observer<Int> {
        Toast.makeText(requireContext(), resources.getString(it), Toast.LENGTH_SHORT).show()
    }

    private val successObserver = Observer<Boolean> {
        binding.btnRegister.isEnabled = it
        if (it) {
            binding.btnRegister.setTextColor(resources.getColor(R.color.white))
        } else {
            binding.btnRegister.setTextColor(resources.getColor(R.color.color_un_enable))
        }
    }
    private val wrongFistNameObserver = Observer<Int> {
        if (it != -1) {
            binding.editFistName.setBackgroundResource(R.drawable.bg_error)
            binding.wrongFistNameText.text = resources.getString(it)
            binding.wrongFistNameText.visibility = View.VISIBLE
        } else {
            binding.editFistName.setBackgroundResource(R.drawable.rectangle_4)
            binding.wrongFistNameText.visibility = View.GONE
        }
    }
    private val wrongLastNameObserver = Observer<Int> {
        if (it != -1) {
            binding.editLastName.setBackgroundResource(R.drawable.bg_error)
            binding.wrongLastNameText.text = resources.getString(it)
            binding.wrongLastNameText.visibility = View.VISIBLE
        } else {
            binding.editLastName.setBackgroundResource(R.drawable.rectangle_4)
            binding.wrongLastNameText.visibility = View.GONE
        }
    }
    private val wrongPhoneObserver = Observer<Int> {
        if (it != -1) {
            binding.editPhoneNumber.setBackgroundResource(R.drawable.bg_error)
            binding.wrongPhoneText.text = resources.getString(it)
            binding.wrongPhoneText.visibility = View.VISIBLE
        } else {
            binding.editPhoneNumber.setBackgroundResource(R.drawable.rectangle_4)
            binding.wrongPhoneText.visibility = View.GONE
        }
    }
    private val wrongPasswordObserver = Observer<Int> {
        if (it != -1) {
            binding.editor1Password.setBackgroundResource(R.drawable.bg_error)
            binding.wrongPasswordText.text = resources.getString(it)
            binding.wrongPasswordText.visibility = View.VISIBLE
        } else {
            binding.editor1Password.setBackgroundResource(R.drawable.rectangle_4)
            binding.wrongPasswordText.visibility = View.GONE
        }
    }

    private val goToNextScreenObserver = Observer<Int> {
        if (it == 0) {

        } else {
            val bundle = Bundle()
            bundle.putString("NAVIGATE","REGISTER")
            findNavController().navigate(R.id.action_registerScreen_to_verificationScreen,bundle)
        }
    }
}