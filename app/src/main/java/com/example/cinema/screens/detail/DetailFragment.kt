package com.example.cinema.screens.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.cinema.MAIN
import com.example.cinema.R
import com.example.cinema.SaveShared

import com.example.cinema.databinding.FragmentDetailBinding
import com.example.cinema.models.MovieItemModel


class DetailFragment : Fragment() {


    private var mBinding: FragmentDetailBinding? = null
    private val binding get() = mBinding!!
    lateinit var currentMovie:MovieItemModel
    private var isFavorite= false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        mBinding = FragmentDetailBinding.inflate(layoutInflater,container,false)
        currentMovie = arguments?.getSerializable("movie") as MovieItemModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        val valueBool = SaveShared.getFavorite(MAIN,currentMovie.id.toString())
        val viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)

        if (isFavorite != valueBool){
            binding.imgDetailFavorite.setImageResource(R.drawable.ic_baseline_favorite)
        }else{
            binding.imgDetailFavorite.setImageResource(R.drawable.ic_baseline_nonfavorite)
        }
        Glide.with(MAIN)
            .load("https://www.themoviedb.org/t/p/w300_and_h450_bestv2${currentMovie.poster_path}")
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(binding.imgDetail)
            binding.tvTitle.text = currentMovie.title
            binding.tvDate.text = currentMovie.release_date
            binding.tvDescription.text = currentMovie.overview

        binding.imgDetailFavorite.setOnClickListener{
            if (isFavorite == valueBool){
                binding.imgDetailFavorite.setImageResource(R.drawable.ic_baseline_favorite)
                SaveShared.setFavorite(MAIN,currentMovie.id.toString(),true)
                viewModel.insert(currentMovie){}
                isFavorite = true
            }else{
                binding.imgDetailFavorite.setImageResource(R.drawable.ic_baseline_nonfavorite)
                viewModel.delete(currentMovie){}
                SaveShared.setFavorite(MAIN,currentMovie.id.toString(),false)
                isFavorite = false
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mBinding = null
    }


}