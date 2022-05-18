package com.example.orgs.database.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.example.orgs.model.Produto

@Dao
interface ProdutoDAO {

    @Query("SELECT * FROM produto")
    fun buscaTodos(): List<Produto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun salva(vararg produto: Produto)

    @Delete
    fun remove(vararg produto: Produto)
}