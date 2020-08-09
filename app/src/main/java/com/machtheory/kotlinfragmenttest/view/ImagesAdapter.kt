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
import kotlinx.android.synthetic.main.image_layout.view.*
import kotlinx.android.synthetic.main.movie_layout.view.*

class ImagesAdapter (val imagesList: ArrayList<Movies>) : RecyclerView.Adapter<ImagesAdapter.ImageListHolder>() {

    val baseURL = "https://image.tmdb.org/t/p/w500"



    fun updateImageList(newImageList: List<Movies>){
        imagesList.clear()
        imagesList.addAll(newImageList)
        Log.i("imageurl", imagesList.toString())
        notifyDataSetChanged()
    }

    class ImageListHolder (var view: View): RecyclerView.ViewHolder(view){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageListHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.image_layout, parent, false)
        return ImageListHolder(view)
    }

    override fun getItemCount()=imagesList.size

    override fun onBindViewHolder(holder: ImageListHolder, position: Int) {

        holder.view.scrollImage.loadImage(baseURL+imagesList[position].imageUrl, getProgressDrawable(holder.view.scrollImage.context))
    }
}