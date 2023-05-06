package com.careem.pizzaorderingapp.domain

data class OrderRequest(
    val name: String,
    val type: Int,
    val size: Int
)