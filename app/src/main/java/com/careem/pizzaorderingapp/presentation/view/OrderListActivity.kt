package com.careem.pizzaorderingapp.presentation.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.careem.pizzaorderingapp.data.OrderRepositoryImpl
import com.careem.pizzaorderingapp.data.Orders.orders
import com.careem.pizzaorderingapp.databinding.ActivityOrderListBinding
import com.careem.pizzaorderingapp.presentation.viewmodel.OrderListViewModel

class OrderListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOrderListBinding

    private val viewModel by lazy { OrderListViewModel(OrderRepositoryImpl()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        handleClicks()
        observeEvents()
    }

    private fun handleClicks() {
        binding.clear.setOnClickListener {
            viewModel.clearOrders()
        }

        binding.refresh.setOnClickListener {
            viewModel.refreshOrders()
        }

        binding.addOrder.setOnClickListener {
            startActivity(Intent(this, AddOrderActivity::class.java))
        }
    }

    private fun observeEvents() {
        viewModel.orders.observe(this) { orders ->
            binding.orderList.text = orders.toString()
        }
    }
}