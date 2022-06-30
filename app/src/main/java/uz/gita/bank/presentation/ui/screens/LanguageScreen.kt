package uz.gita.bank.presentation.ui.screens

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.bank.R
import uz.gita.bank.databinding.ScreenLangulageBinding
import uz.gita.bank.presentation.viewModels.LanguageVM
import uz.gita.bank.presentation.viewModels.impl.LanguageVMImpl
@AndroidEntryPoint
class LanguageScreen : Fragment(R.layout.screen_langulage) {
    private val viewModel: LanguageVM by viewModels<LanguageVMImpl>()
    private val binding by viewBinding(ScreenLangulageBinding::bind)

    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.goToNextScreenLiveData.observe(this@LanguageScreen,goToNextScreenObserver)

        binding.eng.setOnClickListener {
            viewModel.goToNextScreen()
        }
        binding.rus.setOnClickListener {
            viewModel.goToNextScreen()
        }
        binding.eng.setOnClickListener {
            viewModel.goToNextScreen()
        }
    }
    private val goToNextScreenObserver = Observer<Unit>{
        findNavController().navigate(R.id.action_languageScreen_to_privacyPolicyScreen)
    }
}