package com.careem.pizzaorderingapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.careem.pizzaorderingapp.Orders.orders
import com.careem.pizzaorderingapp.databinding.ActivityOrderListBinding

class OrderListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOrderListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.clear.setOnClickListener {
            orders.clear()
            binding.orderList.text = ""
        }

        binding.refresh.setOnClickListener {
            binding.orderList.text = orders.toString()
        }

        binding.addOrder.setOnClickListener {
            startActivity(Intent(this, AddOrderActivity::class.java))
        }
    }
}