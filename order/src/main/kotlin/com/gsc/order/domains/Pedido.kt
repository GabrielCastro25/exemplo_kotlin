package com.gsc.order.domains

import com.gsc.order.domains.enums.StatusEnum
import java.math.BigDecimal
import javax.persistence.*

@Entity
@Table(name = "tb_pedido")
data class Pedido (
        @Id
        @GeneratedValue(GenerationType.AUTO)
        var id: Long?=null,

        @Column("id_produto")
        var idProduto: Long?=null,

        @Column("vl_total")
        var valor: BigDecimal?=null,

        @Column("qt_itens")
        var quantidade: Int?=null,

        @Column("ds_status")
        var status: StatusEnum?=null
)