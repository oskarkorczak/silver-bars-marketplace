package com.korczak.silverbars.domain;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.StringJoiner;

public final class OrderSummary {

	private final BigDecimal quantity;
	private final BigDecimal price;
	private final OrderType type;

	public OrderSummary(BigDecimal quantity, BigDecimal price, OrderType type) {
		this.quantity = quantity;
		this.price = price;
		this.type = type;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public OrderType getType() {
		return type;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}
		if (o == this) {
			return true;
		}
		if (o.getClass() != getClass()) {
			return false;
		}
		OrderSummary rhs = (OrderSummary) o;
		return Objects.equals(this.quantity, rhs.quantity) &&
				Objects.equals(this.price, rhs.price) &&
				Objects.equals(this.type, rhs.type);
	}

	@Override
	public int hashCode() {
		return Objects.hash(quantity, price, type);
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", OrderSummary.class.getSimpleName() + "[", "]")
				.add("quantity=" + quantity)
				.add("price=" + price)
				.add("type=" + type)
				.toString();
	}
}
