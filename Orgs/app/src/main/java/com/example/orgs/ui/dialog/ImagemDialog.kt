package com.example.orgs.ui.dialog

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import com.example.orgs.databinding.ImagemDialogBinding
import com.example.orgs.extensions.tentaCarregar

class ImagemDialog(private val context: Context) {

    fun mostra(urlPadrao: String? = null, quandoCarregado: (url: String) -> Unit) {

        ImagemDialogBinding.inflate(LayoutInflater.from(context)).apply {

            urlPadrao?.let {
                imagemDialogUrl.setText(it)
                imagemDialogImageview.tentaCarregar(it)
            }

            imagemDialogBotaoCarregar.setOnClickListener {
                val url = imagemDialogUrl.text.toString()
                imagemDialogImageview.tentaCarregar(url)
            }

            AlertDialog.Builder(context)
                .setView(root)
                .setPositiveButton("Confirmar"){ _,_ ->
                    val url = imagemDialogUrl.text.toString()
                    quandoCarregado(url)
                }
                .setNegativeButton("Cancelar"){ _,_ ->

                }
                .show()
        }

    }
}