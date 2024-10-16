package com.example.praktikumappf1.bottomnavigations.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.praktikumappf1.bottomnavigations.R
import com.example.praktikumappf1.bottomnavigations.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    companion object{
        val EXTRA_NAME = "extra_name"
        val EXTRA_NIM = "extra_nim"
        val EXTRA_PRODI = "extra_prodi"
        val EXTRA_FAKULTAS = "extra_fakultas"
        val EXTRA_UNIVERSITAS = "extra_universitas"
        val EXTRA_EMAIL = "extra_email"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val name = getString(R.string.creator_name)
        val nim = getString(R.string.creator_NIM)
        val prodi = getString(R.string.creator_prodi)
        val fakultas = getString(R.string.creator_fakultas)
        val universitas = getString(R.string.creator_univ)
        val email = getString(R.string.creator_email)

        binding.moreInfoBtn.setOnClickListener { view ->
            val mBundle = Bundle()
            mBundle.putString(EXTRA_NAME, name)
            mBundle.putString(EXTRA_NIM, nim)
            mBundle.putString(EXTRA_PRODI, prodi)
            mBundle.putString(EXTRA_FAKULTAS, fakultas)
            mBundle.putString(EXTRA_UNIVERSITAS, universitas)
            mBundle.putString(EXTRA_EMAIL, email)

            view.findNavController().navigate(R.id.action_navigation_profile_to_circuitProfileFragment, mBundle)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}