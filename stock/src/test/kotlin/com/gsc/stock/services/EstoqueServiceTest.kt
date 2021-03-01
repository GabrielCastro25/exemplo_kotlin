package com.gsc.stock.services

import com.gsc.stock.domains.Estoque
import com.gsc.stock.repositories.EstoqueRepository
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class EstoqueServiceTest {

    @InjectMocks
    lateinit var service: EstoqueService

    @Mock
    lateinit var repository: EstoqueRepository

    @Test
    fun isPossuiEstoque_quandoEnviaProdutoValidoEQuantidadeValida_esperaTrue() {
        val idProduto = 90L
        val quantidade = 50
        BDDMockito.given(repository.fyndByIdProduto(idProduto)).willReturn(Estoque(1,idProduto, 500))
        val retorno = service.isPossuiEstoque(idProduto, quantidade)
        Assert.assertTrue(retorno)
    }

    @Test
    fun isPossuiEstoque_quandoEnviaProdutoValidoEQuantidadeMaximaValida_esperaTrue() {
        val idProduto = 90L
        val quantidade = 500
        BDDMockito.given(repository.fyndByIdProduto(idProduto)).willReturn(Estoque(1,idProduto, quantidade))
        val retorno = service.isPossuiEstoque(idProduto, quantidade)
        Assert.assertTrue(retorno)
    }

    @Test
    fun isPossuiEstoque_quandoEnviaProdutoValidoEQuantidadeInvalida_esperaFalse() {
        val idProduto = 90L
        val quantidade = 50
        BDDMockito.given(repository.fyndByIdProduto(idProduto)).willReturn(Estoque(1,idProduto, 40))
        val retorno = service.isPossuiEstoque(idProduto, quantidade)
        Assert.assertFalse(retorno)
    }

    @Test
    fun isPossuiEstoque_quandoEnviaProdutoInvalidoEQuantidadeValida_esperaFalse() {
        val idProduto = 90L
        val quantidade = 50
        BDDMockito.given(repository.fyndByIdProduto(idProduto)).willReturn(null)
        val retorno = service.isPossuiEstoque(idProduto, quantidade)
        Assert.assertFalse(retorno)
    }
}