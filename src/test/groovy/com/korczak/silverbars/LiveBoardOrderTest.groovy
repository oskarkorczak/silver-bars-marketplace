package com.korczak.silverbars

import com.korczak.silverbars.domain.Order
import com.korczak.silverbars.domain.OrderSummary
import spock.lang.Specification
import spock.lang.Subject

import static com.korczak.silverbars.domain.OrderType.BUY
import static com.korczak.silverbars.domain.OrderType.SELL
import static java.math.BigDecimal.valueOf

class LiveBoardOrderTest extends Specification {

	@Subject
	LiveOrderBoard board = new LiveOrderBoard()

	Order SELL_ORDER_1 = new Order('user-1', valueOf(3.5), valueOf(306), SELL)
	Order SELL_ORDER_2 = new Order('user-2', valueOf(2.0), valueOf(306), SELL)
	Order BUY_ORDER_1 = new Order('user-3', valueOf(1.5), valueOf(300), BUY)
	Order BUY_ORDER_2 = new Order('user-4', valueOf(0.5), valueOf(310), BUY)


	def 'registers orders'() {
		when:
		board.register(SELL_ORDER_1)
		board.register(BUY_ORDER_1)

		then:
		board.getSummary() == [
				new OrderSummary(valueOf(3.5), valueOf(306), SELL),
				new OrderSummary(valueOf(1.5), valueOf(300), BUY)
		]
	}

	def 'cancels already registered order'() {
		given:
		board.register(SELL_ORDER_1)
		board.register(SELL_ORDER_2)
		board.register(BUY_ORDER_1)

		when:
		board.cancel(SELL_ORDER_2)

		then:
		board.getSummary() == [
				new OrderSummary(valueOf(3.5), valueOf(306), SELL),
				new OrderSummary(valueOf(1.5), valueOf(300), BUY)
		]
	}

	def 'attempts to cancel non-registered order'() {
		given:
		board.register(SELL_ORDER_1)
		board.register(SELL_ORDER_2)
		board.register(BUY_ORDER_1)

		when:
		board.cancel(BUY_ORDER_2)

		then:
		board.getSummary() == [
				new OrderSummary(valueOf(5.5), valueOf(306), SELL),
				new OrderSummary(valueOf(1.5), valueOf(300), BUY)
		]
	}

	def 'correctly merges orders with the same price and sorts them'() {
		when:
		board.register(SELL_ORDER_1)
		board.register(SELL_ORDER_2)
		board.register(new Order('user-5', valueOf(1.2), valueOf(310), SELL))
		board.register(new Order('user-6', valueOf(1.5), valueOf(307), SELL))
		board.register(BUY_ORDER_1)
		board.register(BUY_ORDER_2)

		then:
		board.getSummary() == [
				new OrderSummary(valueOf(5.5), valueOf(306), SELL),
				new OrderSummary(valueOf(1.5), valueOf(307), SELL),
				new OrderSummary(valueOf(1.2), valueOf(310), SELL),
				new OrderSummary(valueOf(0.5), valueOf(310), BUY),
				new OrderSummary(valueOf(1.5), valueOf(300), BUY)
		]
	}
}
