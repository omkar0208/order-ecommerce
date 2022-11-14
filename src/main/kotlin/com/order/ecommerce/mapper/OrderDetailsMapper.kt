package com.order.ecommerce.mapper

import com.order.ecommerce.dto.AddressDto
import com.order.ecommerce.dto.OrderItemDto
import com.order.ecommerce.enum.PaymentStatus
import com.order.ecommerce.model.Address
import com.order.ecommerce.model.OrderItem
import com.order.ecommerce.model.OrderItemPk
import com.order.ecommerce.model.Payment
import com.order.ecommerce.repository.AddressRepository
import com.order.ecommerce.repository.OrderItemRepository
import com.order.ecommerce.repository.PaymentRepository
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.util.*

@Component
class OrderDetailsMapper {

    fun buildOrderItems(
        orderItemsDtoList: List<OrderItemDto>,
        orderId: String
    ): MutableList<OrderItem> {
        val orderItemList = orderItemsDtoList.map { orderItemDto ->
            OrderItem(
                OrderItemPk(orderItemDto.productId, orderId),
                null,
                null,
                orderItemDto.quantity
            )
        }.toMutableList()

        return orderItemList
    }

}
