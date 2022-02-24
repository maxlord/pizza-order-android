package com.careem.coffeeorderingapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.careem.coffeeorderingapp.Orders.orders
import com.careem.coffeeorderingapp.databinding.ActivityAddOrderBinding

class AddOrderActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddOrderBinding

    val coffeeName by lazy { binding.name.text.toString() }
    val order by lazy { OrderRequest(coffeeName, size, type) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.price.text = getString(R.string.price, "0.00")
        handleUIChanges()

        binding.submit.setOnClickListener { submitOrder() }
    }

    // Logic for the price, base price + 0.25 * size (small -> 0, medium -> 1, large -> 2)
    // Base price: Cappuccino (6$), Espresso (5$), Regular (4$)
    // Example: Espresso Medium -> 5 + 5 * 0.25 = 6.25$
    private fun handleUIChanges() {
        binding.typeCappuccino.setOnClickListener {
            type = 0

            binding.typeCappuccino.isChecked = true
            binding.typeEspresso.isChecked = false
            binding.typeRegular.isChecked = false

            val price: Double = when (size) {
                0 -> 6.0
                1 -> 7.5
                2 -> 9.0
                else -> 0.0
            }

            binding.price.text = getString(R.string.price, price.toString())
        }

        binding.typeEspresso.setOnClickListener {
            type = 1

            binding.typeCappuccino.isChecked = false
            binding.typeEspresso.isChecked = true
            binding.typeRegular.isChecked = false

            val price: Double = when (size) {
                0 -> 5.0
                1 -> 6.25
                2 -> 7.5
                else -> 0.0
            }

            binding.price.text = getString(R.string.price, price.toString())
        }

        binding.typeRegular.setOnClickListener {
            type = 2

            binding.typeCappuccino.isChecked = false
            binding.typeEspresso.isChecked = false
            binding.typeRegular.isChecked = true

            val price: Double = when (size) {
                0 -> 4.0
                1 -> 5.0
                2 -> 6.0
                else -> 0.0
            }

            binding.price.text = getString(R.string.price, price.toString())
        }

        binding.sizeSmall.setOnClickListener {
            size = 0

            binding.sizeSmall.isChecked = true
            binding.sizeMedium.isChecked = false
            binding.sizeLarge.isChecked = false

            val basePrice: Double = getBasePrice()
            binding.price.text = getString(R.string.price, basePrice.toString())
        }

        binding.sizeMedium.setOnClickListener {
            size = 1

            binding.sizeSmall.isChecked = false
            binding.sizeMedium.isChecked = true
            binding.sizeLarge.isChecked = false

            val basePrice: Double = getBasePrice()
            binding.price.text = getString(R.string.price, (basePrice + basePrice * 0.25).toString())
        }

        binding.sizeLarge.setOnClickListener {
            size = 2

            binding.sizeSmall.isChecked = false
            binding.sizeMedium.isChecked = false
            binding.sizeLarge.isChecked = true

            val basePrice: Double = getBasePrice()
            binding.price.text = getString(R.string.price, (basePrice + basePrice * 0.5).toString())
        }
    }

    private fun getBasePrice() = when (type) {
        0 -> 6.0
        1 -> 5.0
        2 -> 4.0
        else -> 0.0
    }

    private fun submitOrder() {
        println("order submitted: $order")
        orders.add(order)
        finish()
    }

    companion object {
        var type = 0 // cappuccino -> 0, espresso -> 1, regular -> 2
        var size = 0 // small -> 0, medium -> 1, large -> 2
    }
}
