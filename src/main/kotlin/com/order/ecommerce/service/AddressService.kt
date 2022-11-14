package com.order.ecommerce.service

import com.order.ecommerce.dto.AddressDto
import com.order.ecommerce.model.Address
import com.order.ecommerce.repository.AddressRepository
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.util.*

@Component
class AddressService(private val addressRepository: AddressRepository) {
    fun buildAndLoadAddress(addressDto: AddressDto): Address {
        val addressEntity = addressDto.toAddressEntity();
        return addressRepository.save(addressEntity)
    }

    private fun AddressDto.toAddressEntity() = Address(
        addressId = UUID.randomUUID().toString(),
        address1 = address1,
        address2 = address2,
        city = city,
        state = state,
        zip = zip,
        email = email,
        phone = phone,
        createdAt = LocalDate.now(),
        order = null
    )
}
