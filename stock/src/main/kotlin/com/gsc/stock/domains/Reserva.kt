package com.gsc.stock.domains

import javax.persistence.*

@Entity
@Table(name = "tb_reserva")
data class Reserva (
        @Id
        @GeneratedValue(GenerationType.AUTO)
        var id: Long?=null,

        @Column("id_pedido")
        var idPedido: Long?= null,

        @Column("id_produto")
        var idProduto: Long?= null,

        @Column("qt_itens")
        var quantidade: Int?= null
)