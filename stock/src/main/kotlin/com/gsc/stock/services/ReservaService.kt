package com.gsc.stock.services

import com.gsc.stock.domains.Reserva
import com.gsc.stock.domains.dtos.EstoqueDTO
import com.gsc.stock.domains.dtos.PedidoDTO
import com.gsc.stock.repositories.ReservaRepository
import com.gsc.stock.senders.EstoqueSender
import org.springframework.stereotype.Service

@Service
class ReservaService (private val repository: ReservaRepository,
                      private val estoqueService: EstoqueService,
                      private val sender: EstoqueSender) {

    fun realizarReserva(pedido: PedidoDTO) {
        val podeReservar = this.estoqueService.isPossuiEstoque(pedido.idProduto!!, pedido.quantidade!!)
        val estoqueDTO = if (podeReservar) {
            val reserva = Reserva(idPedido = pedido.id, idProduto = pedido.idProduto, quantidade = pedido.quantidade)
            this.repository.save(reserva)
            EstoqueDTO(idPedido = pedido.id!!, idEstoque = reserva.id, reservado = true)
        } else {
            EstoqueDTO(idPedido = pedido.id!!)
        }
        this.sender.enviarStatusReserva(estoqueDTO)
    }
}