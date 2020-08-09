package com.machtheory.kotlinfragmenttest.view

import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.*

import com.machtheory.kotlinfragmenttest.R
import com.machtheory.kotlinfragmenttest.viewmodel.ImageViewModel
import com.machtheory.kotlinfragmenttest.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : Fragment() {

    private lateinit var viewModel: ListViewModel
    private lateinit var imageViewModel: ImageViewModel
    private var imagesAdapter = ImagesAdapter(arrayListOf())
    private var movieListAdapter = MovieListAdapter(arrayListOf())


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.setTitle(Html.fromHtml("<font color='#FDD835'>MOVIEBOX</font>"))


        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        viewModel.refresh()

        imageViewModel = ViewModelProviders.of(this).get(ImageViewModel::class.java)
        imageViewModel.getImage()

        imageList.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = imagesAdapter
            addItemDecoration(DividerItemDecoration(context, VERTICAL))
        }


        movieList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = movieListAdapter


        }
        refreshLayout.setOnRefreshListener {
            movieList.visibility = View.GONE
            listError.visibility = View.GONE
            progressBar.visibility = View.VISIBLE
            viewModel.refresh()
            imageViewModel.getImage()
            refreshLayout.isRefreshing = false
        }

        observeViewModel()
        observeImageViewModel()
    }

    fun observeImageViewModel() {
        imageViewModel.images.observe(this, Observer { image ->
            image?.let {
                imageList.visibility = View.VISIBLE
                imagesAdapter.updateImageList(image)

            }

        })
    }

    fun observeViewModel() {
        viewModel.movie.observe(this, Observer { movies ->
            movies?.let {
                movieList.visibility = View.VISIBLE
                movieListAdapter.updateMovieList(movies)
            }
        })

        viewModel.movieLoadError.observe(this, Observer { isError ->
            isError?.let {
                listError.visibility = if (it) View.VISIBLE else View.GONE
            }

        })

        viewModel.moviesLoading.observe(this, Observer { isLoading ->
            isLoading?.let {

                progressBar.visibility = if (it) View.VISIBLE else View.GONE
                if (it) {
                    listError.visibility = View.GONE
                    movieList.visibility = View.GONE
                }
            }
        })
    }


}
