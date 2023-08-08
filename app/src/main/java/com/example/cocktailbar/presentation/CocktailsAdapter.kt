package com.example.cocktailbar.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.cocktailbar.R
import com.example.cocktailbar.databinding.CocktailViewBinding
import com.example.cocktailbar.domain.model.Cocktail
import java.util.ArrayList


class CocktailsAdapter(var cocktails: ArrayList<Cocktail>, var listener: Listener): RecyclerView.Adapter<CocktailsAdapter.CocktailsHolder>() {


    class CocktailsHolder(item: View) : RecyclerView.ViewHolder(item) {

        val binding = CocktailViewBinding.bind(item)
        val cornerRadius =
            item.resources.getDimensionPixelSize(R.dimen.corner_radius)

        fun bind(cocktail: Cocktail, listener: Listener) = with(binding) {
            name.text = cocktail.name
            Glide.with(itemView).load(cocktail.image).transform(RoundedCorners(cornerRadius))
                .placeholder(R.drawable.ic_main_empty).into(imageMain)
            itemView.setOnClickListener {
                listener.onClick(cocktail)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CocktailsHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cocktail_view, parent, false)
        return CocktailsHolder(view)
    }

    override fun getItemCount(): Int {
        return cocktails.size
    }

    override fun onBindViewHolder(holder: CocktailsHolder, position: Int) {
        holder.bind(cocktails[position], listener)

    }

    interface Listener {
        fun onClick(cocktail: Cocktail)
    }
}