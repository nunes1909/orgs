package com.example.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.orgs.database.AppDatabase
import com.example.orgs.databinding.ActivityListaProdutosBinding
import com.example.orgs.ui.recylerview.ListaProdutosAdapter

class ListaProdutosActivity : AppCompatActivity() {

    private val produtoAdapter = ListaProdutosAdapter()

    private val binding by lazy {
        ActivityListaProdutosBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configuraRecyclerView()
        configuraFabNovoProduto()
    }

    override fun onResume() {
        super.onResume()
        val produtoDAO = AppDatabase.getInstance(this).getProdutoDAO()
        produtoAdapter.atualiza(produtoDAO.buscaTodos())
    }

    private fun configuraRecyclerView() {
        with(binding.listaProdutosRecyclerview) {
            adapter = produtoAdapter
            layoutManager = LinearLayoutManager(context)
        }
        produtoAdapter.produtoClicadoListener = {
            val intent = Intent(this, DetalhesProdutoActivity::class.java).apply {
                putExtra("produto", it)
            }
            startActivity(intent)
        }
    }

    private fun configuraFabNovoProduto() {
        binding.listaProdutosFabNovoProduto.setOnClickListener {
            val intent = Intent(this, FormularioProdutoActivity::class.java)
            startActivity(intent)
        }
    }
}