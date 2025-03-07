package com.borislav.trafficlightsystemxml.presentation.carinput

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.borislav.trafficlightsystemxml.databinding.FragmentCarInputBinding
import com.borislav.trafficlightsystemxml.di.ServiceLocator

class CarInputFragment : Fragment() {
    
    private var _binding: FragmentCarInputBinding? = null
    private val binding get() = _binding!!
    
    private lateinit var viewModel: CarInputViewModel
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCarInputBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupViewModel()
        setupViews()
        observeViewModel()
    }
    
    private fun setupViewModel() {
        val factory = CarInputViewModelFactory(ServiceLocator.provideValidateCarModelUseCase())
        viewModel = ViewModelProvider(this, factory)[CarInputViewModel::class.java]
    }
    
    private fun setupViews() {
        binding.etCarModel.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.updateCarModel(s.toString())
            }
            
            override fun afterTextChanged(s: Editable?) {}
        })
        
        binding.btnStartDriving.setOnClickListener {
            if (viewModel.validateCarModel()) {
                navigateToTrafficLight(viewModel.carModel.value ?: "")
            }
        }
    }
    
    private fun observeViewModel() {
        viewModel.isError.observe(viewLifecycleOwner) { isError ->
            binding.tilCarModel.error = if (isError) viewModel.errorMessage.value else null
        }
    }
    
    private fun navigateToTrafficLight(carModel: String) {
        val directions = CarInputFragmentDirections.actionCarInputFragmentToTrafficLightFragment(carModel)
        findNavController().navigate(directions)
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 