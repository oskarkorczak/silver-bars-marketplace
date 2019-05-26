package com.korczak.silverbars.domain

import nl.jqno.equalsverifier.EqualsVerifier
import spock.lang.Specification

import static com.korczak.silverbars.domain.OrderType.BUY
import static com.korczak.silverbars.domain.OrderType.SELL
import static org.mutabilitydetector.unittesting.MutabilityAssert.assertImmutable

class OrderSummaryTest extends Specification {

	def 'correctly creates an order summary'() {
		given:
		BigDecimal expectedQuantity = 18.0
		BigDecimal expectedPrice = 500

		when:
		OrderSummary orderSummary = new OrderSummary(expectedQuantity, expectedPrice, orderType)

		then:
		orderSummary.quantity == expectedQuantity
		orderSummary.price == expectedPrice
		orderSummary.type == orderType

		where:
		orderType << [SELL, BUY]
	}

	def 'checks order summary immutability'() {
		expect:
		assertImmutable(OrderSummary)
	}

	def 'checks equals and hashCode contract'() {
		when:
		EqualsVerifier.forClass(OrderSummary).verify()

		then:
		noExceptionThrown()
	}
}
