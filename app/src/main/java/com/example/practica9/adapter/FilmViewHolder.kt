package com.example.listapelisconGET.adapter

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.listapelisconGET.databinding.ItemFilmBinding

class FilmViewHolder(view: View):RecyclerView.ViewHolder(view) {
    private val binding = ItemFilmBinding.bind(view)
    fun render(filmModel: com.example.practica9.Result)  {

        binding.FilmName.text= filmModel.title
        //si observamos la respuesta de la petición en la pag de la api, la url de la imagen no está completa (poster_path), por lo que hay que concatenar lo que falta
        Glide.with(binding.FilmPhoto.context).load("https://image.tmdb.org/t/p/w185/${filmModel.poster_path}").into(binding.FilmPhoto)

        //Configuramos el evento de hacer clic sobre una imagen:
        binding.FilmPhoto.setOnClickListener {Toast.makeText(binding.FilmPhoto.context,filmModel.title,Toast.LENGTH_LONG).show()}
        //Configuramos el evento de hacer clic sobre toda la celda:
        itemView.setOnClickListener {Toast.makeText(binding.FilmPhoto.context,filmModel.title,Toast.LENGTH_LONG).show()}
    }
}