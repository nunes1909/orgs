package com.example.orgs.ui.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.orgs.R
import com.example.orgs.dao.internoDAO
import com.example.orgs.database.AppDatabase
import com.example.orgs.databinding.ActivityFormularioProdutoBinding
import com.example.orgs.extensions.tentaCarregar
import com.example.orgs.model.Produto
import com.example.orgs.ui.dialog.ImagemDialog
import java.math.BigDecimal

class FormularioProdutoActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityFormularioProdutoBinding.inflate(layoutInflater)
    }

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
            val valor = if (campoValor.isBlank()) {
                BigDecimal.ZERO
            } else {
                BigDecimal(campoValor)
            }

            val produtoCriado = Produto(
                nome = campoNome,
                descricao = campoDescricao,
                valor = valor,
                imagem = url
            )

            AppDatabase.getInstance(this).getProdutoDAO().salva(produtoCriado)
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}