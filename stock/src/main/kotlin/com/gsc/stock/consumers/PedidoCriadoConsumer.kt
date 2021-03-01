package com.gsc.stock.consumers

import com.gsc.stock.domains.dtos.PedidoDTO
import com.gsc.stock.services.ReservaService
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class PedidoCriadoConsumer () {

    @Autowired
    lateinit var reservaService: ReservaService

    @RabbitListener(queues = ["\${ordem.criada.queue}"])
    fun novoPedido(pedidoDTO: PedidoDTO) {
        try {
            this.reservaService.realizarReserva(pedidoDTO)
        } catch (e: Throwable) {
            print("Erro: ${e.message}")
        }
    }
}