package com.gsc.stock.senders

import com.gsc.stock.domains.dtos.EstoqueDTO
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class EstoqueSender (){

    @Autowired
    lateinit var template: RabbitTemplate

    @Value("\${reserva.status.exchange}")
    lateinit var exchangeStatusReservaValue: String

    fun enviarStatusReserva(estoqueDTO: EstoqueDTO) {
        try {
            template.convertAndSend(exchangeStatusReservaValue, "", estoqueDTO)
        } catch (e: Throwable) {
            print("Erro: ${e.message}")
        }
    }
}