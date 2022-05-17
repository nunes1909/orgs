package com.example.orgs.ui.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.orgs.R
import com.example.orgs.dao.ProdutoDAO
import com.example.orgs.databinding.ActivityFormularioProdutoBinding
import com.example.orgs.extensions.tentaCarregar
import com.example.orgs.model.Produto
import com.example.orgs.ui.dialog.ImagemDialog

class FormularioProdutoActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityFormularioProdutoBinding.inflate(layoutInflater)
    }
    private val dao = ProdutoDAO()
    private var url: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        title = "Cadastrar produto"

        binding.formularioProdutoImagem.setOnClickListener {
            ImagemDialog(this).mostra(url) {
                url = it
                binding.formularioProdutoImagem.tentaCarregar(url)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.item_menu_formulario, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.item_salvar) {
            val campoNome = binding.formularioProdutoNome.text.toString().trim()
            val campoDescricao = binding.formularioProdutoDescricao.text.toString()
            val campoValor = binding.formularioProdutoValor.text.toString()

            val produtoCriado = Produto(
                nome = campoNome,
                descricao = campoDescricao,
                valor = campoValor,
                imagem = url
            )

            if (validacaoFormulario(produtoCriado)) {
                dao.salva(produtoCriado)
                finish()
            } else {
                Toast.makeText(
                    this,
                    "Para salvar preencha o título",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun validacaoFormulario(produto: Produto): Boolean {
        return !(produto.nome == null || produto.nome == "")
    }
}