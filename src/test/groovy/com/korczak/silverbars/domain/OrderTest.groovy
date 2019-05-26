package com.korczak.silverbars.domain

import nl.jqno.equalsverifier.EqualsVerifier
import spock.lang.Specification

import static com.korczak.silverbars.domain.OrderType.BUY
import static com.korczak.silverbars.domain.OrderType.SELL
import static org.mutabilitydetector.unittesting.MutabilityAssert.assertImmutable

class OrderTest extends Specification {

	def 'correctly creates an order'() {
		given:
		String expectedUserId = 'user-id'
		BigDecimal expectedQuantity = 8.0
		BigDecimal expectedPrice = 100

		when:
		Order order = new Order(expectedUserId, expectedQuantity, expectedPrice, orderType)

		then:
		order.userId == expectedUserId
		order.quantity == expectedQuantity
		order.price == expectedPrice
		order.type == orderType

		where:
		orderType << [SELL, BUY]
	}

	def 'checks order immutability'() {
		expect:
		assertImmutable(Order)
	}

	def 'checks equals and hashCode contract'() {
		when:
		EqualsVerifier.forClass(Order).verify()

		then:
		noExceptionThrown()
	}
}
