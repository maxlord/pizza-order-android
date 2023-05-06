package com.careem.pizzaorderingapp.data

import com.careem.pizzaorderingapp.domain.OrderRepository
import com.careem.pizzaorderingapp.domain.OrderRequest

class OrderRepositoryImpl : OrderRepository {

	override fun putOrder(order: OrderRequest) {
		Orders.orders.add(order)
	}

	override fun getOrders(): List<OrderRequest> {
		return Orders.orders
	}

	override fun clearOrders() {
		Orders.orders.clear()
	}
}