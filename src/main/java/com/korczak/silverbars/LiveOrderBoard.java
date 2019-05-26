package com.korczak.silverbars;

import com.korczak.silverbars.domain.Order;
import com.korczak.silverbars.domain.OrderSummary;
import com.korczak.silverbars.domain.OrderType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static com.korczak.silverbars.domain.OrderType.BUY;
import static com.korczak.silverbars.domain.OrderType.SELL;
import static java.math.BigDecimal.ZERO;
import static java.util.Collections.emptyList;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

public class LiveOrderBoard {

	private final List<Order> orders = new ArrayList<>();


	public void register(Order order) {
		orders.add(order);
	}

	public void cancel(Order order) {
		this.orders.remove(order);
	}

	public List<OrderSummary> getSummary() {
		Map<OrderType, List<Order>> ordersByType = orders
				.stream()
				.collect(groupingBy(Order::getType));

		Map<BigDecimal, List<Order>> sellOrdersByPrice = ordersByPrice(ordersByType, SELL);
		Map<BigDecimal, List<Order>> buyOrdersByPrice = ordersByPrice(ordersByType, BUY);

		List<OrderSummary> result = new ArrayList<>();
		Comparator<OrderSummary> orderSummaryPriceComparator = comparing(OrderSummary::getPrice);
		result.addAll(sort(summarize(sellOrdersByPrice, SELL), orderSummaryPriceComparator));
		result.addAll(sort(summarize(buyOrdersByPrice, BUY), orderSummaryPriceComparator.reversed()));

		return result;
	}

	private Map<BigDecimal, List<Order>> ordersByPrice(Map<OrderType, List<Order>> orders, OrderType type) {
		return orders.getOrDefault(type, emptyList())
				.stream()
				.collect(groupingBy(Order::getPrice));
	}

	private List<OrderSummary> summarize(Map<BigDecimal, List<Order>> ordersByPrice, OrderType orderType) {
		return ordersByPrice.entrySet()
				.stream()
				.map(e -> new OrderSummary(
						sumQuantities(e),
						e.getKey(),
						orderType)
				).collect(toList());
	}

	private BigDecimal sumQuantities(Map.Entry<BigDecimal, List<Order>> orders) {
		return orders.getValue()
				.stream()
				.map(Order::getQuantity)
				.reduce(ZERO, BigDecimal::add);
	}

	private List<OrderSummary> sort(List<OrderSummary> unsortedOrderSummaries, Comparator<OrderSummary> comparator) {
		return unsortedOrderSummaries
				.stream()
				.sorted(comparator)
				.collect(toList());
	}
}
