package com.machtheory.kotlinfragmenttest.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.machtheory.kotlinfragmenttest.R
import com.machtheory.kotlinfragmenttest.util.getProgressDrawable
import com.machtheory.kotlinfragmenttest.util.loadImage
import com.machtheory.kotlinfragmenttest.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.fragment_detail.*


class DetailFragment : Fragment() {

    private lateinit var viewModel : DetailViewModel
    private var uuid = 0
    private var title = "title"
    private var release = "release date"
    private var overview = "overview"
    private var img = "img"
    val baseURL = "https://image.tmdb.org/t/p/w500"


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
        (activity as AppCompatActivity).supportActionBar?.setTitle("")

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.setTitle("")

        viewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)
        viewModel.getMovie()



        arguments?.let {
            uuid = DetailFragmentArgs.fromBundle(it).uuid
            title = DetailFragmentArgs.fromBundle(it).title
            detail_name.text = title
            release = DetailFragmentArgs.fromBundle(it).release
            release_date.text = release
            overview = DetailFragmentArgs.fromBundle(it).overview
            description.text =overview
            img = DetailFragmentArgs.fromBundle(it).img
            detail_image.loadImage(baseURL + img, getProgressDrawable(detail_image.context))

        }

    }


}
