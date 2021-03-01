package com.gsc.stock.repositories

import com.gsc.stock.domains.Estoque
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface EstoqueRepository: JpaRepository<Estoque, Long> {

    @Query(value = "select es from Estoque es where es.idProduto = :idProduto")
    fun fyndByIdProduto(@Param("idProduto") idProduto: Long): Estoque?
}