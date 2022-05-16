package com.example.orgs.dao

import com.example.orgs.model.Produto

class ProdutoDAO {

    fun salva(produto: Produto) = produtos.add(produto)

    fun buscaTodos(): List<Produto> = produtos

    companion object {
        private val produtos = mutableListOf<Produto>(
            Produto(
                nome = "Salada de Frutas",
                descricao = "Maçã, Uva e Laranja",
                valor = "12.99"
            ),
            Produto(
                nome = "Suco verde",
                descricao = "Agua, Couve e Gengibre",
                valor = "12.99"
            )
        )
    }

}