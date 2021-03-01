package com.gsc.stock.services

import com.gsc.stock.domains.Reserva
import com.gsc.stock.domains.dtos.PedidoDTO
import com.gsc.stock.repositories.ReservaRepository
import com.gsc.stock.senders.EstoqueSender
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.*
import org.mockito.junit.MockitoJUnitRunner
import java.math.BigDecimal

@RunWith(MockitoJUnitRunner::class)
class ReservaServiceTest {

    @InjectMocks
    lateinit var service: ReservaService

    @Mock
    lateinit var repository: ReservaRepository

    @Mock
    lateinit var estoqueService: EstoqueService

    @Mock
    lateinit var sender: EstoqueSender

    val pedidoDTO = PedidoDTO(1,20, BigDecimal.TEN,5,"Teste")

    @Test
    fun realizarReserva_quandoEnviaPedidoValido_esperaOk() {
        BDDMockito.given(estoqueService.isPossuiEstoque(pedidoDTO.idProduto!!, pedidoDTO.quantidade!!)).willReturn(true)
        this.service.realizarReserva(pedidoDTO)
        Mockito.verify(repository, Mockito.atLeastOnce()).save(ArgumentMatchers.any(Reserva::class.java))
    }

    @Test
    fun realizarReserva_quandoEnviaPedidoSemEstoque_esperaOk() {
        BDDMockito.given(estoqueService.isPossuiEstoque(pedidoDTO.idProduto!!, pedidoDTO.quantidade!!)).willReturn(false)
        this.service.realizarReserva(pedidoDTO)
        Mockito.verify(repository, Mockito.never()).save(ArgumentMatchers.any(Reserva::class.java))
    }

}