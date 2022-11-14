package com.order.ecommerce.controller

import com.order.ecommerce.dto.OrderCreateResponse
import com.order.ecommerce.dto.OrderDto
import com.order.ecommerce.model.Order
import com.order.ecommerce.service.OrderService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1")
class OrderController(val orderService: OrderService) {

    @PostMapping("/orders")
    @Operation(summary = "Create an order", description = "Create an order")
    fun createOrder(@RequestBody orderDto: OrderDto): ResponseEntity<OrderCreateResponse> {
        return ResponseEntity(orderService.createOrder(orderDto), HttpStatus.CREATED)
    }

    @GetMapping("/orders/{orderId}")
    fun findOrderById(@PathVariable(name = "orderId") orderId: String): ResponseEntity<Order> {
        return ResponseEntity(orderService.findOrderById(orderId), HttpStatus.OK)
    }

    @PatchMapping("/orders/{orderId}")
    fun updateOrderStatus(
        @PathVariable("orderId") orderId: String,
        @RequestParam(name = "orderStatus") orderStatus: String
    ): ResponseEntity<String> {
        orderService.updateOrderStatus(orderId, orderStatus)
        return ResponseEntity.ok("resource order updated")
    }

    @ExceptionHandler(MissingServletRequestParameterException::class)
    fun handleException(ex: MissingServletRequestParameterException): ResponseEntity<String> {
        return ResponseEntity("Missing request parameter ${ex.parameterName}", HttpStatus.BAD_REQUEST)
    }
}
