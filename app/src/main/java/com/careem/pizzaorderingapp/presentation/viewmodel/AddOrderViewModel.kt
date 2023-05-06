package com.careem.pizzaorderingapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.careem.pizzaorderingapp.domain.OrderRequest
import com.careem.pizzaorderingapp.domain.OrderRepository

class AddOrderViewModel(private val orderRepo: OrderRepository): ViewModel() {
	private val _pizzaName: MutableLiveData<String> = MutableLiveData("")
	val pizzaName: LiveData<String>
		get() = _pizzaName

	private val _type: MutableLiveData<Int> = MutableLiveData(0)
	val type: LiveData<Int>
		get() = _type
	private val _size: MutableLiveData<Int> = MutableLiveData(0)
	val size: LiveData<Int>
		get() = _size
	private val _price: MutableLiveData<Double> = MutableLiveData(0.0)
	val price: LiveData<Double>
		get() = _price
	private val _submitEnabled: MutableLiveData<Boolean> = MutableLiveData(false)
	val submitEnabled: LiveData<Boolean>
		get() = _submitEnabled

	init {
		recalculatePrice()
		validateRequest()
	}

	fun setPizzaName(name: String) {
		_pizzaName.value = name
		validateRequest()
	}

	private fun validateRequest() {
		_submitEnabled.value = _pizzaName.value.orEmpty().isNotBlank()
	}

	fun setTypeValue(typeValue: Int) {
		_type.value = typeValue
		recalculatePrice()
		validateRequest()
	}

	fun setSizeValue(sizeValue: Int) {
		_size.value = sizeValue
		recalculatePrice()
		validateRequest()
	}

	// Logic for the price, basePrice + (basePrice * 0.25 * size) (size: small -> 0, medium -> 1, large -> 2)
	// Base price: Margherita (6$), Mushroom (5$), Vegetarian (4$)
	// Example: Margherita Large -> 5 + 5 * 0.25 * 2 = 7.5
	// Example: Margherita Medium -> 5 + 5 * 0.25 * 1 = 6.25$
	// Example: Margherita Small -> 5 + 5 * 0.25 * 0 = 5
	private fun recalculatePrice() {
		val basePrice = getBasePrice()
		_price.value = basePrice + (basePrice * 0.25 * (_size.value ?: 0))
	}

	private fun getBasePrice() = when (_type.value) {
		TYPE_MARGHERITA -> 6.0
		TYPE_MUSHROOM -> 5.0
		TYPE_VEGETARIAN -> 4.0
		else -> 0.0
	}

	fun submitOrder() {
		val order = buildOrder()
		println("order submitted: $order")
		orderRepo.putOrder(order)
	}

	private fun buildOrder(): OrderRequest {
		return OrderRequest(_pizzaName.value.orEmpty(), _type.value ?: 0, _size.value ?: 0)
	}

	companion object {
		const val TYPE_MARGHERITA = 0
		const val TYPE_MUSHROOM = 1
		const val TYPE_VEGETARIAN = 2

		const val SIZE_SMALL = 0
		const val SIZE_MEDIUM = 1
		const val SIZE_LARGE = 2
	}
}