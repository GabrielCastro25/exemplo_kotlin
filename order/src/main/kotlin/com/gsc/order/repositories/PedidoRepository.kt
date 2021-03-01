package com.gsc.order.repositories

import com.gsc.order.domains.Pedido
import org.springframework.data.jpa.repository.JpaRepository

interface PedidoRepository: JpaRepository<Pedido, Long> {
}