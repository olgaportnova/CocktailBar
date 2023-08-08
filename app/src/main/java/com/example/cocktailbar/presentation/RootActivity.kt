package com.example.cocktailbar.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.example.cocktailbar.R
import com.example.cocktailbar.databinding.ActivityRootBinding
import com.example.cocktailbar.databinding.FragmentHomeCocktailsListBinding

class RootActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRootBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityRootBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            // Добавляем фрагмент в контейнер
            supportFragmentManager.commit {
                this.add(R.id.container_view, HomeCocktailsListFragment())
            }
        }


    }

}