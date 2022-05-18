package com.example.orgs.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.orgs.R
import com.example.orgs.databinding.ActivityDetalhesProdutoBinding
import com.example.orgs.extensions.formataParaMoedaBrasileira
import com.example.orgs.extensions.tentaCarregar
import com.example.orgs.model.Produto

class DetalhesProdutoActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityDetalhesProdutoBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        intent.getParcelableExtra<Produto>("produto")?.let {
            with(binding){
                detalhesProdutoImagem.tentaCarregar(it.imagem)
                val moedaFormatada = it.valor.formataParaMoedaBrasileira()
                detalhesProdutoValor.text = moedaFormatada
                detalhesProdutoNome.text = it.nome
                detalhesProdutoDescricao.text = it.descricao
            }
        } ?: finish()
    }
}