package com.careem.pizzaorderingapp.presentation.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.careem.pizzaorderingapp.R
import com.careem.pizzaorderingapp.data.OrderRepositoryImpl
import com.careem.pizzaorderingapp.databinding.ActivityAddOrderBinding
import com.careem.pizzaorderingapp.presentation.viewmodel.AddOrderViewModel

class AddOrderActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddOrderBinding

    private val viewModel by lazy { AddOrderViewModel(OrderRepositoryImpl()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)
        handleUIChanges()
        observeEvents()
    }

    private fun handleUIChanges() {
        binding.name.addTextChangedListener {
            viewModel.setPizzaName(it.toString())
        }

        binding.typeMargherita.setOnClickListener {
            viewModel.setTypeValue(AddOrderViewModel.TYPE_MARGHERITA)
        }

        binding.typeMushroom.setOnClickListener {
            viewModel.setTypeValue(AddOrderViewModel.TYPE_MUSHROOM)
        }

        binding.typeVegetarian.setOnClickListener {
            viewModel.setTypeValue(AddOrderViewModel.TYPE_VEGETARIAN)
        }

        binding.sizeSmall.setOnClickListener {
            viewModel.setSizeValue(AddOrderViewModel.SIZE_SMALL)
        }

        binding.sizeMedium.setOnClickListener {
            viewModel.setSizeValue(AddOrderViewModel.SIZE_MEDIUM)
        }

        binding.sizeLarge.setOnClickListener {
            viewModel.setSizeValue(AddOrderViewModel.SIZE_LARGE)
        }

        binding.submit.setOnClickListener {
            viewModel.submitOrder()
            finish()
        }
    }

    private fun observeEvents() {
        viewModel.type.observe(this) { type ->
            binding.typeMargherita.isChecked = type == AddOrderViewModel.TYPE_MARGHERITA
            binding.typeMushroom.isChecked = type == AddOrderViewModel.TYPE_MUSHROOM
            binding.typeVegetarian.isChecked = type == AddOrderViewModel.TYPE_VEGETARIAN
        }
        viewModel.size.observe(this) { size ->
            binding.sizeSmall.isChecked = size == AddOrderViewModel.SIZE_SMALL
            binding.sizeMedium.isChecked = size == AddOrderViewModel.SIZE_MEDIUM
            binding.sizeLarge.isChecked = size == AddOrderViewModel.SIZE_LARGE
        }
        viewModel.price.observe(this) { price ->
            binding.price.text = getString(R.string.price, price.toString())
        }
        viewModel.submitEnabled.observe(this) { enabled ->
            binding.submit.isEnabled = enabled
        }
    }
}