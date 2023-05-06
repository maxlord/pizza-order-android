package com.careem.pizzaorderingapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.careem.pizzaorderingapp.domain.OrderRepository
import com.careem.pizzaorderingapp.domain.OrderRequest

class OrderListViewModel(private val orderRepo: OrderRepository) : ViewModel() {

	private val _orders: MutableLiveData<List<OrderRequest>> = MutableLiveData()
	val orders: LiveData<List<OrderRequest>>
		get() = _orders

	fun clearOrders() {
		orderRepo.clearOrders()
		_orders.value = emptyList()
	}

	fun refreshOrders() {
		_orders.value = orderRepo.getOrders()
	}
}