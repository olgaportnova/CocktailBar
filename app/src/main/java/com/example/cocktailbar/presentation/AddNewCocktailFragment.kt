package com.example.cocktailbar.presentation

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.get
import androidx.core.view.isNotEmpty
import com.example.cocktailbar.R
import com.example.cocktailbar.databinding.FragmentAddNewCocktailBinding
import com.example.cocktailbar.domain.model.Cocktail
import com.google.android.material.chip.Chip
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddNewCocktailFragment : Fragment() {

    private var _binding: FragmentAddNewCocktailBinding? = null
    private val binding get() = _binding!!
    private val cocktailsViewModel: CocktailsViewModel by viewModel()


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

        // добавление ингредов
        binding.buttonAddIngr.setOnClickListener {
            addIngerds()
        }

        // сохранение + проверка все ли поля введены + на экран гланый
        binding.buttonSave.setOnClickListener {
            if (binding.siBtnName.text.isEmpty()) {
                Toast.makeText(requireContext(), R.string.enter_name, Toast.LENGTH_LONG).show()
                binding.hintTitle.setTextColor(Color.parseColor("#FF0000"))
                binding.hint2Title.setTextColor(Color.parseColor("#FF0000"))
                binding.siBtnName.requestFocus()

            }
            else if (binding.chipGroup.childCount == 0) {
                Toast.makeText(requireContext(), R.string.enter_ing, Toast.LENGTH_LONG)
                    .show()
                binding.hintIngred.setTextColor(Color.parseColor("#FF0000"))
                binding.hint2Ingred.setTextColor(Color.parseColor("#FF0000"))
                binding.edIngred.requestFocus()
            } else {
                val cocktail = createNewCocktailForSP()
                cocktailsViewModel.addNewCocktailToFav(cocktail)
                openHomeFragment()
            }


        }

        if (binding.siBtnName.hasFocus()) {
            binding.hintTitle.setTextColor(Color.parseColor("#FF0000"))
            binding.hint2Title.setTextColor(Color.parseColor("#FF0000"))
        }

        // возрат на главный экран
        binding.buttonCancel.setOnClickListener {
            openHomeFragment()
        }
    }


    // возвращаемся на главный экран
    private fun openHomeFragment() {
        activity?.supportFragmentManager
            ?.beginTransaction()
            ?.replace(R.id.container_view, HomeCocktailsListFragment())
            ?.addToBackStack(null)
            ?.commit();

    }

    // формируем cocktail из введенных данных
    private fun createNewCocktailForSP(): Cocktail {
        return Cocktail(
            binding.imageView.toString(),
            binding.siBtnName.text.toString(),
            binding.edDescrip.text.toString(),
            binding.edRecipe.text.toString(),
            createListOfIng()
        )
    }

    // добавление ингредов
    private fun addIngerds() {
        if (binding.edIngred.text.toString().isNotEmpty()) {

            val chip = Chip(requireContext())
            chip.text = binding.edIngred.text.toString()
            chip.isCloseIconVisible = true

            chip.setChipIconResource(com.google.android.material.R.drawable.mtrl_dialog_background)

            chip.setOnCloseIconClickListener {
                binding.chipGroup.removeView(chip)
            }

            binding.chipGroup.addView(chip)
            binding.edIngred.setText("")
        }
    }

    // формируем список из ингредов
    private fun createListOfIng(): List<String> {
        val ingList = mutableListOf<String>()
        for (i in 0 until binding.chipGroup.childCount) {
            val a = binding.chipGroup.getChildAt(i) as Chip
            ingList.add(a.text.toString())
        }
        return ingList
    }


    companion object {
        @JvmStatic
        fun newInstance() = AddNewCocktailFragment()
    }
}

