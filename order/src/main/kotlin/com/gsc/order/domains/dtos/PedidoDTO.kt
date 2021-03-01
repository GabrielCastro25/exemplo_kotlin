package com.gsc.order.domains.dtos

import com.gsc.order.domains.enums.StatusEnum
import java.math.BigDecimal
import javax.validation.constraints.NotNull

data class PedidoDTO (
        @field:NotNull(message = "O id do produto deve ser preenchido") var idProduto: Long,
        @field:NotNull(message = "O valor deve ser preenchido") var valor: BigDecimal,
        @field:NotNull(message = "A quantidade deve ser preenchida") var quantidade: Int) {
    fun getStatus(): StatusEnum = StatusEnum.CRIADO
}