package com.borislav.trafficlightsystemxml.presentation.trafficlight

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.borislav.trafficlightsystemxml.R
import com.borislav.trafficlightsystemxml.databinding.FragmentTrafficLightBinding
import com.borislav.trafficlightsystemxml.di.ServiceLocator
import com.borislav.trafficlightsystemxml.domain.model.TrafficLightState

class TrafficLightFragment : Fragment() {
    
    companion object {
        private const val ANIMATION_DURATION = 500L // 500ms for fade animation
    }
    
    private var _binding: FragmentTrafficLightBinding? = null
    private val binding get() = _binding!!
    
    private lateinit var viewModel: TrafficLightViewModel
    private val args by navArgs<TrafficLightFragmentArgs>()
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTrafficLightBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupViewModel()
        setupViews()
        observeViewModel()
    }
    
    private fun setupViewModel() {
        val factory = TrafficLightViewModelFactory(
            ServiceLocator.provideGetTrafficLightStateUseCase(),
            args.carModel
        )
        viewModel = ViewModelProvider(this, factory)[TrafficLightViewModel::class.java]
    }
    
    private fun setupViews() {
        binding.tvCarModel.text = getString(R.string.car_model_display, args.carModel)
    }
    
    private fun observeViewModel() {
        viewModel.currentState.observe(viewLifecycleOwner) { state ->
            updateTrafficLights(state)
        }
    }
    
    private fun updateTrafficLights(state: TrafficLightState) {
        animateLight(binding.ivRedLight, state == TrafficLightState.RED)
        animateLight(binding.ivOrangeLight, state == TrafficLightState.ORANGE)
        animateLight(binding.ivGreenLight, state == TrafficLightState.GREEN)
    }
    
    private fun animateLight(view: ImageView, isActive: Boolean) {
        val targetAlpha = if (isActive) 1.0f else 0.3f
        ObjectAnimator.ofFloat(view, "alpha", view.alpha, targetAlpha).apply {
            duration = ANIMATION_DURATION
            interpolator = AccelerateDecelerateInterpolator()
            start()
        }
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 