package com.example.tiptime.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.text.NumberFormat

class TipViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(TipUiState())
    val uiState: StateFlow<TipUiState> = _uiState.asStateFlow()

    init {
        resetState()
    }

    private fun calculateTip(
        amountInput: String = _uiState.value.amountInput,
        tipInput: String = _uiState.value.tipInput,
        roundUp: Boolean = _uiState.value.roundUp
    ): String {
        val amount: Double = amountInput.toDoubleOrNull() ?: 0.0
        val tipPercent: Double = tipInput.toDoubleOrNull() ?: 0.0

        var tip = tipPercent / 100 * amount
        if (roundUp)
            tip = kotlin.math.ceil(tip)
        return NumberFormat.getCurrencyInstance().format(tip)
    }

    fun onBillAmountChange(newAmount: String) {
        _uiState.update { currentState ->
            currentState.copy(
                amountInput = newAmount,
                tip = calculateTip(amountInput = newAmount)
            )
        }
    }

    fun onTipPercentageChange(newTip: String) {
        _uiState.update { currentState ->
            currentState.copy(
                tipInput = newTip,
                tip = calculateTip(tipInput = newTip)
            )
        }
    }

    fun onRoundUpChange(newRoundUp: Boolean) {
        _uiState.update { currentState ->
            currentState.copy(
                roundUp = newRoundUp,
                tip = calculateTip(roundUp = newRoundUp)
            )
        }
    }

    fun resetState() {
        _uiState.value = TipUiState()
    }

}