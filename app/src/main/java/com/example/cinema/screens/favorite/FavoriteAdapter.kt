package com.example.cinema.screens.favorite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cinema.MAIN
import com.example.cinema.R
import com.example.cinema.models.MovieItemModel
import com.example.cinema.screens.main.MainFragment
import com.example.cinema.screens.main.MainFragment.Companion.clickMovie

class FavoriteAdapter:RecyclerView.Adapter<FavoriteAdapter.MyViewHolder>() {

    private var listMovies = emptyList<MovieItemModel>()

    class MyViewHolder(view:View):RecyclerView.ViewHolder(view) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.item_title).text = listMovies[position].title
        holder.itemView.findViewById<TextView>(R.id.item_date).text = listMovies[position].release_date

        Glide.with(MAIN)
            .load("https://www.themoviedb.org/t/p/w300_and_h450_bestv2${listMovies[position].poster_path}")
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(holder.itemView.findViewById(R.id.item_img))
    }

    override fun getItemCount(): Int {
        return listMovies.size
    }

    fun setList(list: List<MovieItemModel>){
        listMovies = list
        notifyDataSetChanged() // не лучший вариант, но упор на ретрофит тут
    }

    override fun onViewAttachedToWindow(holder: MyViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.itemView.setOnClickListener{
            FavoriteFragment.clickMovie(listMovies[holder.adapterPosition])
        }
    }

    override fun onViewDetachedFromWindow(holder: MyViewHolder) {
        holder.itemView.setOnClickListener(null)
    }

}