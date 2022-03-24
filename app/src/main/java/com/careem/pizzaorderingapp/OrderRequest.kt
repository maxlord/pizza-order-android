package com.careem.pizzaorderingapp

data class OrderRequest(
    val name: String,
    val type: Int,
    val size: Int
)