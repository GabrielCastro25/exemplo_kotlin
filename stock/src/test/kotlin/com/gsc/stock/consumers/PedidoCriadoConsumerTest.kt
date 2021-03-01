package com.gsc.stock.consumers

import com.gsc.stock.domains.dtos.PedidoDTO
import com.gsc.stock.services.ReservaService
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.math.BigDecimal

@RunWith(MockitoJUnitRunner::class)
class PedidoCriadoConsumerTest {

    @InjectMocks
    lateinit var consumer: PedidoCriadoConsumer

    @Mock
    lateinit var service: ReservaService

    val pedido = PedidoDTO(1,1, BigDecimal.TEN,5,"")

    @Test
    fun novoPedido_quandoEnviaObjetoValido_esperaOk() {
        consumer.novoPedido(pedido)
        Mockito.verify(service, Mockito.atLeastOnce()).realizarReserva(pedido)
    }

    @Test
    fun novoPedido_quandoEnviaObjetoInvalido_esperaNullPointer() {
        Mockito.doThrow(NullPointerException("teste")).`when`(service).realizarReserva(pedido)
        consumer.novoPedido(pedido)
        Mockito.verify(service, Mockito.atLeastOnce()).realizarReserva(pedido)
    }
}