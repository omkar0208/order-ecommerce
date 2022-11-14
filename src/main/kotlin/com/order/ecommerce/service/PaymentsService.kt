package com.order.ecommerce.service

import com.order.ecommerce.enum.PaymentStatus
import com.order.ecommerce.model.Payment
import com.order.ecommerce.repository.PaymentRepository
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.util.*

@Component
class PaymentsService(
    private val paymentRepository: PaymentRepository
) {
    fun buildAndLoadPayment(amount: Double, paymentMode: String): Payment {
        val payment = Payment(
            UUID.randomUUID().toString(),
            amount,
            paymentMode,
            UUID.randomUUID().toString(),
            PaymentStatus.PROCESSING.name,
            LocalDate.now(),
            null
        )
        return paymentRepository.save(payment)
    }
}
