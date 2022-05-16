package com.example.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.orgs.dao.ProdutoDAO
import com.example.orgs.databinding.ActivityListaProdutosBinding
import com.example.orgs.ui.recylerview.ListaProdutosAdapter

class ListaProdutosActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityListaProdutosBinding.inflate(layoutInflater)
    }

    private val dao = ProdutoDAO()
    private val produtoAdapter = ListaProdutosAdapter(listaProdutos = dao.buscaTodos())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configuraRecyclerView()
        configuraFabNovoProduto()
    }

    override fun onResume() {
        super.onResume()
        produtoAdapter.atualiza(dao.buscaTodos())
    }

    private fun configuraRecyclerView() {
        with(binding.listaProdutosRecyclerview) {
            adapter = produtoAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun configuraFabNovoProduto() {
        binding.listaProdutosFabNovoProduto.setOnClickListener {
            val intent = Intent(this, FormularioProdutoActivity::class.java)
            startActivity(intent)
        }
    }
}