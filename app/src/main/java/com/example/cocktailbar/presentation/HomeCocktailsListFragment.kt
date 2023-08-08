package com.example.cocktailbar.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cocktailbar.R
import com.example.cocktailbar.databinding.FragmentAddNewCocktailBinding
import com.example.cocktailbar.databinding.FragmentHomeCocktailsListBinding
import com.example.cocktailbar.databinding.FragmentHomeEmptyListBinding
import com.example.cocktailbar.domain.model.Cocktail
import com.example.cocktailbar.domain.model.State
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeCocktailsListFragment : Fragment(), CocktailsAdapter.Listener {

    private lateinit var rv: RecyclerView
    private lateinit var cocktailsAdapter: CocktailsAdapter
    private var favList = mutableListOf<Cocktail>()
    private val cocktailsViewModel: CocktailsViewModel by viewModel()


    private var _binding: FragmentHomeEmptyListBinding? = null
    private val binding get() = _binding!!





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeEmptyListBinding.inflate(inflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cocktailsViewModel.getFavListStatusLiveData().observe(viewLifecycleOwner) { updatedStatus ->
            updatedViewBasedOnStatus(updatedStatus)
        }

        init()

        binding.btAddNew.setOnClickListener {
            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.container_view, AddNewCocktailFragment())
                ?.addToBackStack(null)
                ?.commit();
        }



    }

    private fun init() {
        binding.rvCocktailsList.layoutManager = GridLayoutManager(requireContext(),2)
        cocktailsViewModel.showFavList()
    }

    fun updatedViewBasedOnStatus(updatedStatus: State) {
        when {
            updatedStatus.favList.isEmpty() -> showEmpty()
            else -> showContent(updatedStatus.favList)
        }

    }

    fun showEmpty() {

        binding.apply {
            rvCocktailsList.visibility = View.GONE
            tvHeaderNotEmpty.visibility=View.GONE


        }



    }

    fun showContent(favList: List<Cocktail>) {
        binding.apply {
            rvCocktailsList.adapter = CocktailsAdapter(ArrayList(favList), this@HomeCocktailsListFragment )
            rvCocktailsList.visibility = View.VISIBLE
            tvHeaderNotEmpty.visibility=View.VISIBLE
            btAddNew.visibility=View.GONE
            tvHintText.visibility=View.GONE
            imageCover.visibility=View.GONE
            tvHeaderText.visibility=View.GONE
            arrowMain.visibility=View.GONE


        }
    }

    override fun onClick(cocktail: Cocktail) {
        cocktailsViewModel.openDetails(cocktail)
    }



}
