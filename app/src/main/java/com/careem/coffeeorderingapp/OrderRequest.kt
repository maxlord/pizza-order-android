package com.careem.coffeeorderingapp

data class OrderRequest(
    val name: String,
    val type: Int,
    val size: Int
)