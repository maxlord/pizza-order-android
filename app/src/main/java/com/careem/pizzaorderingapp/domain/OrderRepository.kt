package com.careem.pizzaorderingapp.domain


interface OrderRepository {

	fun putOrder(order: OrderRequest)

	fun getOrders(): List<OrderRequest>

	fun clearOrders()
}