package com.example.praktikumappf1.bottomnavigations.ui.profile

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.praktikumappf1.bottomnavigations.databinding.FragmentCircuitProfileBinding

class CircuitProfileFragment : Fragment() {
    private var _binding: FragmentCircuitProfileBinding? = null
    private val binding get() = _binding!!

    private var creatorName: String? = null
    private var creatorNIM: String? = null
    private var creatorProdi: String? = null
    private var creatorFakultas: String? = null
    private var creatorUniv: String? = null
    private var creatorEmail: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCircuitProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val buttonToGithub = binding.button1
        val buttonToInstagram = binding.button2
        val buttonToShare = binding.button3

        buttonToGithub.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://github.com/Banozz")
            startActivity(intent)
        }

        buttonToInstagram.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://www.instagram.com/ibrahim_nrhd/")
            startActivity(intent)
        }

        buttonToShare.setOnClickListener {
            val intent = Intent().apply {
                action = Intent.ACTION_SEND
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT,
                    "Name: $creatorName\n" +
                            "NIM: $creatorNIM\n" +
                            "Prodi: $creatorProdi\n" +
                            "Fakultas: $creatorFakultas\n" +
                            "Universitas: $creatorUniv\n" +
                            "Email: $creatorEmail")
            }
            startActivity(Intent.createChooser(intent, "Share with"))
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        creatorName = arguments?.getString(ProfileFragment.EXTRA_NAME)
        creatorNIM = arguments?.getString(ProfileFragment.EXTRA_NIM)
        creatorProdi = arguments?.getString(ProfileFragment.EXTRA_PRODI)
        creatorFakultas = arguments?.getString(ProfileFragment.EXTRA_FAKULTAS)
        creatorUniv = arguments?.getString(ProfileFragment.EXTRA_UNIVERSITAS)
        creatorEmail = arguments?.getString(ProfileFragment.EXTRA_EMAIL)

        binding.textTitleProfile.text = "$creatorName's Link Tree!"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}