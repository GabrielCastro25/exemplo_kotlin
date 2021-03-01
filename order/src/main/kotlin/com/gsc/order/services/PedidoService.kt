package com.gsc.order.services

import com.gsc.order.domains.Pedido
import com.gsc.order.domains.dtos.EstoqueDTO
import com.gsc.order.domains.dtos.PedidoDTO
import com.gsc.order.domains.enums.StatusEnum
import com.gsc.order.repositories.PedidoRepository
import com.gsc.order.senders.PedidoSender
import javassist.NotFoundException
import org.modelmapper.ModelMapper
import org.modelmapper.convention.MatchingStrategies
import org.springframework.stereotype.Service

@Service
class PedidoService(private val repository: PedidoRepository,
                    private val sender: PedidoSender) {

    val modelMapper = ModelMapper()

    fun save(dto: PedidoDTO): Pedido {
        modelMapper.configuration.matchingStrategy = MatchingStrategies.STRICT
        val pedido =  modelMapper.map(dto, Pedido::class.java)
        repository.save(pedido)
        this.sender.enviarSolicitacaoReserva(pedido)
        return pedido
    }

    fun getById(id:Long) = this.repository.findById(id).orElseThrow { NotFoundException("Pedido não encontrado")}

    fun atualizarReserva(estoqueDTO: EstoqueDTO) {
        val pedido = this.getById(estoqueDTO.idPedido!!)

        pedido.status = if (estoqueDTO.reservado!!) {
            StatusEnum.CONFIRMADO
        } else {
            StatusEnum.CANCELADO
        }
        this.repository.save(pedido)
    }
}