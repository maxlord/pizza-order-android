package com.careem.pizzaorderingapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.careem.pizzaorderingapp.Orders.orders
import com.careem.pizzaorderingapp.databinding.ActivityAddOrderBinding

class AddOrderActivity : AppCompatActivity() {

    lateinit var binding: ActivityAddOrderBinding

    val pizzaName by lazy { binding.name.text.toString() }
    val order by lazy { OrderRequest(pizzaName, size, type) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.price.text = getString(R.string.price, "0.00")
        handleUIChanges()

        binding.submit.setOnClickListener { submitOrder() }
    }

    // Logic for the price, basePrice + (basePrice * 0.25 * size) (size: small -> 0, medium -> 1, large -> 2)
    // Base price: Margherita (6$), Mushroom (5$), Vegetarian (4$)
    // Example: Margherita Large -> 5 + 5 * 0.25 * 2 = 7.5
    // Example: Margherita Medium -> 5 + 5 * 0.25 * 1 = 6.25$
    // Example: Margherita Small -> 5 + 5 * 0.25 * 0 = 5
    private fun handleUIChanges() {
        binding.typeMargherita.setOnClickListener {
            type = 0

            binding.typeMargherita.isChecked = true
            binding.typeMushroom.isChecked = false
            binding.typeVegetarian.isChecked = false

            val price: Double = when (size) {
                0 -> 6.0
                1 -> 7.5
                2 -> 9.0
                else -> 0.0
            }

            binding.price.text = getString(R.string.price, price.toString())
        }

        binding.typeMushroom.setOnClickListener {
            type = 1

            binding.typeMargherita.isChecked = false
            binding.typeMushroom.isChecked = true
            binding.typeVegetarian.isChecked = false

            val price: Double = when (size) {
                0 -> 5.0
                1 -> 6.25
                2 -> 7.5
                else -> 0.0
            }

            binding.price.text = getString(R.string.price, price.toString())
        }

        binding.typeVegetarian.setOnClickListener {
            type = 2

            binding.typeMargherita.isChecked = false
            binding.typeMushroom.isChecked = false
            binding.typeVegetarian.isChecked = true

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
        var type = 0 // margherita -> 0, mushroom -> 1, vegetarian -> 2
        var size = 0 // small -> 0, medium -> 1, large -> 2
    }
}
