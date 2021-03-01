package com.gsc.order.domains.dtos

data class EstoqueDTO (
        var idPedido: Long?=null,
        var idEstoque: Long?=null,
        var reservado: Boolean?=false)