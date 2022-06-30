package uz.gita.bank.presentation.ui.screens

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.bank.R
import uz.gita.bank.databinding.ScreenSplashBinding
import uz.gita.bank.presentation.viewModels.SplashVM
import uz.gita.bank.presentation.viewModels.impl.SplashVMImpl

@SuppressLint("CustomSplashScreen")
class SplashScreen : Fragment(R.layout.screen_splash) {
    private val viewModel : SplashVM by viewModels<SplashVMImpl>()
    private val binding by viewBinding(ScreenSplashBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.goToLanguageScreen()
        viewModel.goToNextScreenLiveData.observe(viewLifecycleOwner,goToNextScreenObserver)
    }
    private val goToNextScreenObserver = Observer<Int>{
        if (it == 0){
            findNavController().navigate(R.id.action_splashScreen_to_languageScreen)
        }else{

        }
    }
}