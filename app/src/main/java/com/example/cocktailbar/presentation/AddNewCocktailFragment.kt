package com.example.cocktailbar.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cocktailbar.R
import com.example.cocktailbar.databinding.FragmentAddNewCocktailBinding

class AddNewCocktailFragment : Fragment() {

    private var _binding: FragmentAddNewCocktailBinding? = null
    private val binding get() = _binding!!




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddNewCocktailBinding.inflate(inflater, container, false)
        return binding.root


    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonAddIngr.setOnClickListener{
            if (binding.edIngred.text.toString().isNotEmpty()) {
                addChip(binding.edIngred.text.toString())

                binding.edIngred.setText("")

            }
        }



    }

    private fun addChip(text:String) {
        val chip = Chip(this)
        chip.text = text
        chip.isCloseIconVisible = true


    }

    companion object {
        @JvmStatic
        fun newInstance() = AddNewCocktailFragment()
    }
}