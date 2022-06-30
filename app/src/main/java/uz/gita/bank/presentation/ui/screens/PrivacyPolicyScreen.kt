package uz.gita.bank.presentation.ui.screens

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
import uz.gita.bank.databinding.ScreenPravicyPolicyBinding
import uz.gita.bank.presentation.viewModels.PrivacyPolicyVM
import uz.gita.bank.presentation.viewModels.impl.PrivacyPolicyVMImpl


@AndroidEntryPoint
class PrivacyPolicyScreen : Fragment(R.layout.screen_pravicy_policy) {
    private val viewModel: PrivacyPolicyVM by viewModels<PrivacyPolicyVMImpl>()
    private val binding by viewBinding(ScreenPravicyPolicyBinding::bind)
    private var isTrue = false

    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.goToNextScreenLiveData.observe(this@PrivacyPolicyScreen, goToNextScreenObserver)
        binding.checkBox.setOnClickListener {
            if (!isTrue) {
                binding.checkBox.setBackgroundResource(R.drawable.check)
                binding.btnAccept.isEnabled = true
                binding.btnAccept.setTextColor(resources.getColor(R.color.white))
            } else {
                binding.checkBox.setBackgroundResource(R.drawable.circleun)
                binding.btnAccept.isEnabled = false
                binding.btnAccept.setTextColor(resources.getColor(R.color.color_un_enable))
            }
            isTrue = !isTrue
        }
        binding.btnAccept.setOnClickListener {
            if (isTrue) {
                viewModel.goToNextScreen()
            } else {
                Toast.makeText(requireContext(), "Please agree our privacy policy", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private val goToNextScreenObserver = Observer<Unit> {
        findNavController().navigate(R.id.action_privacyPolicyScreen_to_loginScreen)
    }
}