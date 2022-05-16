package com.example.orgs.ui.recylerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.orgs.databinding.ItemProdutoBinding
import com.example.orgs.model.Produto

class ListaProdutosAdapter(
    listaProdutos: List<Produto>
) : RecyclerView.Adapter<ListaProdutosAdapter.ProdutoViewHolder>() {

    private val produtos = listaProdutos.toMutableList()

    class ProdutoViewHolder(binding: ItemProdutoBinding) : RecyclerView.ViewHolder(binding.root){
        private val binding = binding

        fun vincula(produto: Produto) {
            val campoNome = binding.itemProdutoNome
            campoNome.text = produto.nome

            val campoDescricao = binding.itemProdutoDescricao
            campoDescricao.text = produto.descricao

            val campoValor = binding.itemProdutoValor
            campoValor.text = produto.valor
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