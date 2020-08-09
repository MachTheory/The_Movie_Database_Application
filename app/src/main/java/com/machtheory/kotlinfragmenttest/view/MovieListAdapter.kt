package com.machtheory.kotlinfragmenttest.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.machtheory.kotlinfragmenttest.R
import com.machtheory.kotlinfragmenttest.model.Movies
import com.machtheory.kotlinfragmenttest.util.getProgressDrawable
import com.machtheory.kotlinfragmenttest.util.loadImage
import kotlinx.android.synthetic.main.movie_layout.view.*

class MovieListAdapter(val movieList: ArrayList<Movies>) :
    RecyclerView.Adapter<MovieListAdapter.MovieListHolder>() {

    val baseURL = "https://image.tmdb.org/t/p/w500"


    fun updateMovieList(newMovieList: List<Movies>) {
        movieList.clear()
        movieList.addAll(newMovieList)
        Log.i("movie list", movieList.toString())
        notifyDataSetChanged()
    }

    class MovieListHolder(var view: View) : RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.movie_layout, parent, false)
        return MovieListHolder(view)
    }

    override fun getItemCount() = movieList.size

    override fun onBindViewHolder(holder: MovieListHolder, position: Int) {
        holder.view.name.text = movieList[position].movieName
        holder.view.release.text = movieList[position].releaseDate
        holder.view.duration.text = movieList[position].duration
        holder.view.ratingBar.progress= movieList[position].rating?.toInt()!!*10
        holder.view.setOnClickListener {
            val action: ListFragmentDirections.ActionDetail = ListFragmentDirections.actionDetail()
            action.title = movieList[position].movieName.toString()
            action.release = movieList[position].releaseDate.toString()
            action.overview = movieList[position].description.toString()
            action.img = movieList[position].imageUrl.toString()
            Navigation.findNavController(it).navigate(action)
        }
        holder.view.imageView.loadImage(
            baseURL + movieList[position].imageUrl,
            getProgressDrawable(holder.view.imageView.context)
        )
    }
}