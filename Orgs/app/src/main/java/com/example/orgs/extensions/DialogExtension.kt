package com.example.orgs.extensions

import android.widget.ImageView
import coil.load
import com.example.orgs.R

fun ImageView.tentaCarregar(url: String? = null){
    load(url){
        fallback(R.drawable.erro)
        error(R.drawable.erro)
        placeholder(androidx.appcompat.R.color.material_grey_600)
    }
}