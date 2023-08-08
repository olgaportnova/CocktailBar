package com.example.cocktailbar.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cocktailbar.R
import com.example.cocktailbar.databinding.FragmentHomeCocktailsListBinding
import com.example.cocktailbar.domain.model.Cocktail
import com.example.cocktailbar.domain.model.State
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeCocktailsListFragment : Fragment(), CocktailsAdapter.Listener {

    private val cocktailsViewModel: CocktailsViewModel by viewModel()
    private var _binding: FragmentHomeCocktailsListBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeCocktailsListBinding.inflate(inflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cocktailsViewModel.getFavListStatusLiveData().observe(viewLifecycleOwner) { updatedStatus ->
            updatedViewBasedOnStatus(updatedStatus)
        }

        init()

        binding.btAddNew.setOnClickListener {
            toAddFragment()
        }
        binding.btAddNewNotEmpty.setOnClickListener {
            toAddFragment()
        }

    }

    private fun init() {
        binding.rvCocktailsList.layoutManager = GridLayoutManager(requireContext(), 2)
        cocktailsViewModel.showFavList()
    }

    override fun onClick(cocktail: Cocktail) {
        toDetailsFragment(cocktail)

    }


    // navigation
    private fun toAddFragment(){
        activity?.supportFragmentManager
            ?.beginTransaction()
            ?.replace(R.id.container_view, AddNewCocktailFragment())
            ?.addToBackStack(null)
            ?.commit();
    }

    private fun toDetailsFragment(cocktail: Cocktail) {

        fun getDetailsFragment(cocktail: Cocktail) = DetailsFragment().apply {
            arguments = bundleOf("NAME_KEY" to cocktail)
        }
        activity?.supportFragmentManager
            ?.beginTransaction()
            ?.replace(R.id.container_view, getDetailsFragment(cocktail))
            ?.addToBackStack(null)
            ?.commit()

    }



    // updating UI based on List empty or not
    fun updatedViewBasedOnStatus(updatedStatus: State) {
        when {
            updatedStatus.favList.isEmpty() -> showEmpty()
            else -> showContent(updatedStatus.favList)
        }

    }

    fun showEmpty() {
        binding.apply {
            rvCocktailsList.visibility = View.GONE
            tvHeaderNotEmpty.visibility = View.GONE
            btAddNewNotEmpty.visibility = View.GONE
        }
    }

    fun showContent(favList: List<Cocktail>) {
        binding.apply {
            rvCocktailsList.adapter =
                CocktailsAdapter(ArrayList(favList), this@HomeCocktailsListFragment)
            rvCocktailsList.visibility = View.VISIBLE
            tvHeaderNotEmpty.visibility = View.VISIBLE
            btAddNew.visibility = View.GONE
            tvHintText.visibility = View.GONE
            imageCover.visibility = View.GONE
            tvHeaderText.visibility = View.GONE
            arrowMain.visibility = View.GONE
        }
    }



}
