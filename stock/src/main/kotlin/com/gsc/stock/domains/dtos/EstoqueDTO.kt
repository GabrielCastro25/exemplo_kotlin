package com.gsc.stock.domains.dtos

data class EstoqueDTO (
        val idPedido: Long,
        var idEstoque: Long?=null,
        var reservado: Boolean?=false)