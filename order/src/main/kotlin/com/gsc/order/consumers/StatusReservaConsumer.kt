package com.gsc.order.consumers

import com.gsc.order.domains.dtos.EstoqueDTO
import com.gsc.order.services.PedidoService
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component

@Component
class StatusReservaConsumer (private val service: PedidoService) {

    @RabbitListener(queues = ["\${reserva.status.queue}"])
    fun novoPedido(estoqueDTO: EstoqueDTO) {
        try {
            this.service.atualizarReserva(estoqueDTO)
        } catch (e: Throwable) {
            e.printStackTrace()
        }
    }
}