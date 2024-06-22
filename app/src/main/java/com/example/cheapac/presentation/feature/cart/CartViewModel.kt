package com.example.cheapac.presentation.feature.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cheapac.data.Resource
import com.example.cheapac.data.UiState
import com.example.cheapac.data.local.entity.CartItem
import com.example.cheapac.data.repository.CartRepository
import com.example.cheapac.domain.use_case.ClearCartUseCase
import com.example.cheapac.domain.use_case.GetCartUseCase
import com.example.cheapac.presentation.feature.cart.checkout.payment.PaymentEvent
import com.example.cheapac.presentation.feature.cart.checkout.payment.PaymentInfo
import com.example.cheapac.presentation.feature.cart.checkout.shipping.ShippingEvent
import com.example.cheapac.presentation.feature.cart.checkout.shipping.ShippingInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartRepository: CartRepository,
    private val getCartUseCase: GetCartUseCase,
    private val clearCartUseCase: ClearCartUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(CartUiState())
    val uiState = _uiState.asStateFlow()

    private fun getCart() {
        getCartUseCase().map { result ->
            when (result) {
                is Resource.Loading -> {
                    _uiState.update { it.copy(cart = UiState(isLoading = true)) }
                }

                is Resource.Error -> {
                    _uiState.update { it.copy(cart = UiState(message = result.message)) }
                }

                is Resource.Success -> {
                    result.data?.let { data ->
                        _uiState.update { it.copy(cart = UiState(data = data)) }
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun incrementQuantity(cartItem: CartItem) {
        viewModelScope.launch {
            val items = (uiState.value.cart.data ?: emptyList()).toMutableList()
            if (items.isNotEmpty()) {
                val index = items.indexOf(cartItem)
                val item = items[index]
                items[index] = item.copy(quantity = item.quantity + 1)
                cartRepository.incrementQuantity(items[index])

                _uiState.update {
                    it.copy(
                        cart = UiState(items)
                    )
                }
            }
        }
    }

    private fun decrementQuantity(cartItem: CartItem) {
        viewModelScope.launch {
            val items = (uiState.value.cart.data ?: emptyList()).toMutableList()
            if (items.isNotEmpty()) {
                val index = items.indexOf(cartItem)
                val item = items[index]
                items[index] = item.copy(quantity = item.quantity - 1)
                cartRepository.decrementQuantity(items[index])

                _uiState.update {
                    it.copy(
                        cart = UiState(items)
                    )
                }
            }
        }
    }

    private fun clear() {
        clearCartUseCase().map { result ->
            if (result) {
                _uiState.update {
                    it.copy(
                        cart = UiState(data = emptyList()),
                        shippingInfo = ShippingInfo(),
                        paymentInfo = PaymentInfo()
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    fun onEvent(event: CartEvent) {
        when (event) {
            is CartEvent.InitialFetch -> {
                getCart()
            }

            is CartEvent.Clear -> {
                clear()
            }

            is CartEvent.IncrementQuantity -> {
                incrementQuantity(cartItem = event.cartItem)
            }

            is CartEvent.DecrementQuantity -> {
                decrementQuantity(cartItem = event.cartItem)
            }
        }
    }

    fun onEvent(event: ShippingEvent) {
        when (event) {
            is ShippingEvent.FirstNameChanged -> {
                _uiState.update {
                    it.copy(
                        shippingInfo = it.shippingInfo.copy(
                            firstName = event.firstName
                        )
                    )
                }
            }

            is ShippingEvent.LastNameChanged -> {
                _uiState.update {
                    it.copy(
                        shippingInfo = it.shippingInfo.copy(
                            lastName = event.lastName
                        )
                    )
                }
            }

            is ShippingEvent.PhoneNumberChanged -> {
                _uiState.update {
                    it.copy(
                        shippingInfo = it.shippingInfo.copy(
                            phoneNumber = event.phoneNumber
                        )
                    )
                }
            }

            is ShippingEvent.AddressChanged -> {
                _uiState.update {
                    it.copy(
                        shippingInfo = it.shippingInfo.copy(
                            address = event.address
                        )
                    )
                }
            }

            is ShippingEvent.CountrySelected -> {
                _uiState.update {
                    it.copy(
                        shippingInfo = it.shippingInfo.copy(
                            country = event.country
                        )
                    )
                }
            }
        }
    }

    fun onEvent(event: PaymentEvent) {
        when (event) {
            is PaymentEvent.CardHolderChanged -> {
                val isMaxLengthReached = event.cardHolder.length == 27
                if (!isMaxLengthReached) {
                    _uiState.update {
                        it.copy(
                            paymentInfo = it.paymentInfo.copy(
                                cardHolder = event.cardHolder
                            )
                        )
                    }
                }
            }

            is PaymentEvent.CardNumberChanged -> {
                _uiState.update {
                    it.copy(
                        paymentInfo = it.paymentInfo.copy(
                            cardNumber = event.cardNumber
                        )
                    )
                }
            }

            is PaymentEvent.CVCChanged -> {
                val isMaxCharReached = event.cvc.length == 4
                if (!isMaxCharReached) {
                    _uiState.update {
                        it.copy(
                            paymentInfo = it.paymentInfo.copy(
                                cvc = event.cvc
                            )
                        )
                    }
                }
            }

            is PaymentEvent.ExpirationDateChanged -> {
                _uiState.update {
                    it.copy(
                        paymentInfo = it.paymentInfo.copy(
                            expirationDate = event.expirationDate
                        )
                    )
                }
            }
        }
    }
}
