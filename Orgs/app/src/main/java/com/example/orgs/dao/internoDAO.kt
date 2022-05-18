package com.example.orgs.dao

import com.example.orgs.model.Produto
import java.math.BigDecimal

class internoDAO {

    fun salva(produto: Produto) = produtos.add(produto)

    fun buscaTodos(): List<Produto> = produtos.toList()

    companion object {
        private val produtos = mutableListOf<Produto>(
            Produto(
                nome = "Salada de Frutas",
                descricao = "Maçã, Uva e Laranja",
                valor = BigDecimal("12.99")
            )
        )
    }

}