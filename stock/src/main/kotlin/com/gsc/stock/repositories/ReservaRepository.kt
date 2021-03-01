package com.gsc.stock.repositories

import com.gsc.stock.domains.Reserva
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ReservaRepository: JpaRepository<Reserva, Long> {

}