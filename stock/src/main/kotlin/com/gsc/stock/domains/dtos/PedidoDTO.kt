package com.gsc.stock.domains.dtos

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.math.BigDecimal

data class PedidoDTO (
        var id: Long?=null,
        var idProduto: Long?=null,
        var valor: BigDecimal?=null,
        var quantidade: Int?=null,
        val status: String?=null
)