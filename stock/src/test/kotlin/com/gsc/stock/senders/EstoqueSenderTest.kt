package com.gsc.stock.senders

import com.gsc.stock.domains.dtos.EstoqueDTO
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.test.util.ReflectionTestUtils

@RunWith(MockitoJUnitRunner::class)
class EstoqueSenderTest {

    @InjectMocks
    lateinit var sender: EstoqueSender

    @Mock
    lateinit var template: RabbitTemplate

    @Test
    fun enviarStatusReserva_quandoEnviaObjetoValido_esperaOk() {
        val exchangeTest = "fwefew5345"
        ReflectionTestUtils.setField(sender, "exchangeStatusReservaValue", exchangeTest)
        val request = EstoqueDTO(idEstoque = 1, idPedido = 3)
        sender.enviarStatusReserva(request)
        Mockito.verify(template, Mockito.atLeastOnce()).convertAndSend(exchangeTest, "", request)
    }

    @Test
    fun enviarStatusReserva_quandoEnviaObjetoInvalido_esperaNullPointer() {
        val exchangeTest = "fwefew5345"
        ReflectionTestUtils.setField(sender, "exchangeStatusReservaValue", exchangeTest)
        val request = EstoqueDTO(idEstoque = 1, idPedido = 3)
        Mockito.doThrow(NullPointerException("teste")).`when`(template).convertAndSend(exchangeTest, "", request)
        sender.enviarStatusReserva(request)
        Mockito.verify(template, Mockito.atLeastOnce()).convertAndSend(exchangeTest, "", request)
    }
}