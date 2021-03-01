package com.gsc.stock.services

import com.gsc.stock.repositories.EstoqueRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class EstoqueService (private val repository: EstoqueRepository) {

    fun isPossuiEstoque(idProduto: Long, qtdSolicitada: Int): Boolean {
        val estoque = this.repository.fyndByIdProduto(idProduto)

        return if (estoque?.quantidadeDisponivel!! >= qtdSolicitada) {
            estoque.quantidadeDisponivel = (estoque.quantidadeDisponivel!! - qtdSolicitada)
            repository.save(estoque)
            true
        } else {
            false
        }
    }

}