package com.example.exam10

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.exam10.databinding.WinFragmentBinding

class WinFragment : Fragment() {

    private var _binding: WinFragmentBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if(_binding == null){
            _binding = WinFragmentBinding.inflate(layoutInflater)
            init()
        }
        return binding.root
    }

    private fun init(){
        binding.btnPlayAgain.setOnClickListener {
            findNavController().navigate(R.id.action_winFragment_to_gameFragment)
        }
    }

}