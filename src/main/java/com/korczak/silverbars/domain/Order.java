package com.korczak.silverbars.domain;

import java.math.BigDecimal;
import java.util.Objects;

public final class Order {

	private final String userId;
	private final BigDecimal quantity;
	private final BigDecimal price;
	private final OrderType type;

	public Order(String userId, BigDecimal quantity, BigDecimal price, OrderType type) {
		this.userId = userId;
		this.quantity = quantity;
		this.price = price;
		this.type = type;
	}

	public String getUserId() {
		return userId;
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
		Order rhs = (Order) o;
		return Objects.equals(this.userId, rhs.userId) &&
				Objects.equals(this.quantity, rhs.quantity) &&
				Objects.equals(this.price, rhs.price) &&
				Objects.equals(this.type, rhs.type);
	}

	@Override
	public int hashCode() {
		return Objects.hash(userId, quantity, price, type);
	}
}
