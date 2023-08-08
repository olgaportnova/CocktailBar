package com.example.cocktailbar.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.cocktailbar.R
import com.example.cocktailbar.databinding.FragmentDetailsBinding
import com.example.cocktailbar.domain.model.Cocktail


class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val cocktail = requireArguments().getSerializable("NAME_KEY") as Cocktail
        fillDetailsData(cocktail)


    }

    private fun fillDetailsData(cocktail:Cocktail) {
        binding.name.text = cocktail.name
        binding.recip.text = cocktail.recep
        binding.descrip.text = cocktail.discrip
        val arrayAdapter = ArrayAdapter(
            requireContext(),
            R.layout.list_item_layout, R.id.textItem,
            cocktail.ing
        )
        binding.increds.adapter = arrayAdapter

    }

    companion object {
        const val NAME_KEY ="name"
        @JvmStatic
        fun newInstance(cocktail: Cocktail) = DetailsFragment().apply {
            arguments = bundleOf(NAME_KEY to cocktail)
        }
    }
}