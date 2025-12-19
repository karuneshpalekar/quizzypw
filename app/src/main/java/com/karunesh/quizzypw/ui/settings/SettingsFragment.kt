package com.karunesh.quizzypw.ui.settings


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.karunesh.quizzypw.R
import com.karunesh.quizzypw.databinding.FragmentSettingsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.log


@AndroidEntryPoint
class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ivBack.setOnClickListener{
            findNavController().popBackStack()
        }
        binding.logout.setOnClickListener { logout() }
    }

    private fun logout() {
        FirebaseAuth.getInstance().signOut()
        findNavController().navigate(SettingsFragmentDirections.actionSettingsFragmentToLoginFragment())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}