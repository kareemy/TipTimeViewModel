package com.example.tiptime.ui

import java.text.NumberFormat

data class TipUiState(
    val amountInput: String = "",
    val tipInput: String = "",
    val roundUp: Boolean = false,

    val tip: String = NumberFormat.getCurrencyInstance().format(0)
)
