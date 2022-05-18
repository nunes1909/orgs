package com.example.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.orgs.R
import com.example.orgs.database.AppDatabase
import com.example.orgs.databinding.ActivityDetalhesProdutoBinding
import com.example.orgs.extensions.formataParaMoedaBrasileira
import com.example.orgs.extensions.tentaCarregar
import com.example.orgs.model.Produto

class DetalhesProdutoActivity : AppCompatActivity() {

    private lateinit var produto: Produto
    private val binding by lazy {
        ActivityDetalhesProdutoBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        intent.getParcelableExtra<Produto>("produto")?.let {
            produto = it
            with(binding) {
                detalhesProdutoImagem.tentaCarregar(it.imagem)
                val moedaFormatada = it.valor.formataParaMoedaBrasileira()
                detalhesProdutoValor.text = moedaFormatada
                detalhesProdutoNome.text = it.nome
                detalhesProdutoDescricao.text = it.descricao
            }
        } ?: finish()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.item_menu_detalhes, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (::produto.isInitialized) {
            val db = AppDatabase.getInstance(this).getProdutoDAO()
            when (item.itemId) {
                R.id.item_menu_detalhes_remover -> {
                    AlertDialog.Builder(this)
                        .setTitle("Removendo produto")
                        .setMessage("Você realmente quer remover o produto?")
                        .setPositiveButton("Sim") { _, _ ->
                            db.remove(produto)
                            finish()
                        }
                        .setNegativeButton("Não") { _, _ ->

                        }
                        .show()
                }

                R.id.item_menu_detalhes_editar -> {
                    AlertDialog.Builder(this)
                        .setTitle("Editando produto")
                        .setMessage("Você realmente quer editar o produto?")
                        .setPositiveButton("Sim") { _, _ ->
                            Intent(
                                this,
                                FormularioProdutoActivity::class.java
                            ).apply {
                                putExtra("produto", produto)
                                startActivity(this)
                            }
                            finish()
                        }
                        .setNegativeButton("Não") { _, _ ->

                        }
                        .show()
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }
}