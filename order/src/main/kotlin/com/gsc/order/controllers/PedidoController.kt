package com.gsc.order.controllers

import com.gsc.order.domains.dtos.PedidoDTO
import com.gsc.order.services.PedidoService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("v1/pedidos")
class PedidoController(private val service: PedidoService) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun criar(@Valid @RequestBody request: PedidoDTO) = this.service.save(request)

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun obter(@PathVariable("id") id: Long) = this.service.getById(id)
}