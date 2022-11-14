package com.order.ecommerce.controller

import com.order.ecommerce.dto.ProductDto
import com.order.ecommerce.model.Product
import com.order.ecommerce.service.ProductService
import io.swagger.v3.oas.annotations.Operation
import lombok.extern.slf4j.Slf4j
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1")
@Slf4j
class ProductController(val productService: ProductService) {

    @PostMapping("/products")
    @Operation(summary = "Create a product", description = "Create a product")
    fun createProduct(@RequestBody productDto: ProductDto): ResponseEntity<Product> {
        return ResponseEntity(productService.createProduct(productDto), HttpStatus.CREATED)
    }

    @GetMapping("/products/{productId}")
    @Operation(summary = "Get a product", description = "Get a product")
    fun getProduct(@PathVariable(name = "productId") productId: String): ResponseEntity<Product> {
        return ResponseEntity.ok(productService.getProduct(productId))
    }

    @ExceptionHandler(MissingServletRequestParameterException::class)
    fun handleException(ex: MissingServletRequestParameterException): ResponseEntity<String> {
        return ResponseEntity("Missing request parameter ${ex.parameterName}", HttpStatus.BAD_REQUEST)
    }
}
