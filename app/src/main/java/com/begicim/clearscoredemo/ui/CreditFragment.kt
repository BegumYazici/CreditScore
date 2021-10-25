package com.begicim.clearscoredemo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.begicim.clearscoredemo.databinding.FragmentCreditBinding
import com.begicim.clearscoredemo.util.NetworkStatusUtil
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CreditFragment : Fragment(){

    private val creditViewModel: CreditViewModel by viewModels()

    @Inject lateinit var bindingAdapters : NetworkStatusUtil

    private var _binding: FragmentCreditBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreditBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        creditViewModel.loadCreditScore()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        creditViewModel.apiStatus.observe(viewLifecycleOwner, {
            bindingAdapters.bindApiStatusImage(binding.creditScoreViewGroup,binding.loadingStatusViewGroup,binding.statusImage, it)
        })

        creditViewModel.progress.observe(viewLifecycleOwner,{
            binding.progressBar.progress = it
        })

        creditViewModel.creditScore.observe(viewLifecycleOwner, { creditModel ->
            binding.textViewCreditScore.text = creditModel.creditScore.toString()
            binding.textViewMaxScore.text = "out of ${creditModel.creditMaxValueScore}"
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val CREDIT_FRAGMENT_TAG = "CreditFragment"
    }
}