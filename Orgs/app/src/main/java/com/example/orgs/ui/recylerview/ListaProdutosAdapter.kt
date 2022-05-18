package com.example.orgs.ui.recylerview

import android.util.Log
import android.view.LayoutInflater
import android.view.View.GONE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.orgs.databinding.ItemProdutoBinding
import com.example.orgs.extensions.formataParaMoedaBrasileira
import com.example.orgs.extensions.tentaCarregar
import com.example.orgs.model.Produto
import java.text.NumberFormat
import java.util.*

class ListaProdutosAdapter(
    listaProdutos: List<Produto> = emptyList(),
    var produtoClicadoListener: (produto: Produto) -> Unit = {}
) : RecyclerView.Adapter<ListaProdutosAdapter.ProdutoViewHolder>() {

    private val produtos = listaProdutos.toMutableList()

    inner class ProdutoViewHolder(binding: ItemProdutoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val binding = binding

        private lateinit var produtoIniciado: Produto

        init {
            itemView.setOnClickListener {
                if (::produtoIniciado.isInitialized) {
                    produtoClicadoListener(produtoIniciado)
                    Log.i("TAG locona", "item clicado: ")
                }
            }
        }

        fun vincula(produto: Produto) {
            produtoIniciado = produto
            binding.apply {

                itemProdutoNome.text = produto.nome

                itemProdutoDescricao.text = produto.descricao

                val moedaFormatada: String = produto.valor.formataParaMoedaBrasileira()
                itemProdutoValor.text = moedaFormatada

                if (produto.imagem != null) {
                    itemProdutoImagem.tentaCarregar(produto.imagem)
                } else {
                    itemProdutoImagem.visibility = GONE
                }

            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProdutoViewHolder {
        val binding = ItemProdutoBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ProdutoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProdutoViewHolder, position: Int) {
        val produtoPelaPosicao = produtos[position]
        holder.vincula(produtoPelaPosicao)
    }

    override fun getItemCount() = produtos.size

    fun atualiza(todos: List<Produto>) {
        produtos.clear()
        produtos.addAll(todos)
        notifyDataSetChanged()
    }
}