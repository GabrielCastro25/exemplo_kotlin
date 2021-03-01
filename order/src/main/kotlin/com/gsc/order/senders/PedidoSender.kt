package com.gsc.order.senders

import com.fasterxml.jackson.core.JsonProcessingException
import com.gsc.order.domains.Pedido
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component


@Component
class PedidoSender (private val template: RabbitTemplate){

    @Value("\${ordem.criada.exchange}")
    lateinit var exchangeOrdemCriadaValue: String

    fun enviarSolicitacaoReserva(pedido: Pedido) {
        try {
            template.convertAndSend(exchangeOrdemCriadaValue, "", pedido)
        } catch (e: Throwable) {
            e.printStackTrace()
        }
    }
}