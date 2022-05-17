package com.example.orgs.ui.recylerview

import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.core.view.marginLeft
import androidx.recyclerview.widget.RecyclerView
import com.example.orgs.databinding.ItemProdutoBinding
import com.example.orgs.extensions.tentaCarregar
import com.example.orgs.model.Produto
import java.text.NumberFormat
import java.util.*

class ListaProdutosAdapter(
    listaProdutos: List<Produto>
) : RecyclerView.Adapter<ListaProdutosAdapter.ProdutoViewHolder>() {

    private val produtos = listaProdutos.toMutableList()

    class ProdutoViewHolder(binding: ItemProdutoBinding) : RecyclerView.ViewHolder(binding.root) {
        private val binding = binding

        fun vincula(produto: Produto) {

            binding.apply {

                val campoNome = itemProdutoNome
                campoNome.text = produto.nome

                val campoDescricao = itemProdutoDescricao
                campoDescricao.text = produto.descricao

                val campoValor = itemProdutoValor
                val formatador = NumberFormat.getCurrencyInstance(Locale("pt", "br"))
                val moedaFormatada: String = formatador.format(produto.valor.toDouble())
                campoValor.text = moedaFormatada

                if (produto.imagem != null){
                    itemProdutoImagem.tentaCarregar(produto.imagem)
                }else{
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