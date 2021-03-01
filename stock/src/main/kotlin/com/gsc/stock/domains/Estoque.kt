package com.gsc.stock.domains

import javax.persistence.*

@Entity
@Table(name = "tb_estoque")
data class Estoque (
        @Id
        @GeneratedValue(GenerationType.AUTO)
        var id: Long?=null,

        @Column("id_produto")
        var idProduto: Long?= null,

        @Column("qt_disponivel")
        var quantidadeDisponivel: Int?= null
)