package com.example.cheapac.presentation.feature.cart.checkout.payment

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cheapac.R
import com.example.cheapac.presentation.common.CheapacIcons
import com.example.cheapac.presentation.component.CheapacTextField
import com.example.cheapac.presentation.theme.AppTheme

@Composable
fun PaymentPage(onEvent: (PaymentEvent) -> Unit, markAsDone: () -> Unit, uiState: PaymentInfo) {
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(
                    color = MaterialTheme.colorScheme.error,
                    shape = RoundedCornerShape(8.dp)
                )
        ) {
            Text(
                text = "EXAMPLE BANK",
                color = MaterialTheme.colorScheme.onError,
                fontSize = 16.sp,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(start = 24.dp, top = 24.dp)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterEnd)
                    .padding(horizontal = 24.dp)
            ) {
                Text(
                    text = uiState.cardNumber.ifEmpty { stringResource(R.string.cc_card_number_placeholder) },
                    color = MaterialTheme.colorScheme.onError,
                    letterSpacing = 1.sp,
                    fontSize = 16.sp,
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = stringResource(R.string.valid_thru).uppercase(),
                    color = MaterialTheme.colorScheme.onError,
                    fontSize = 10.sp,
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = uiState.expirationDate.uppercase()
                        .ifEmpty { stringResource(R.string.cc_exp_date_placeholder) },
                    color = MaterialTheme.colorScheme.onError,
                    fontSize = 10.sp,
                )
            }

            Text(
                text = uiState.cardHolder.uppercase()
                    .ifEmpty { stringResource(R.string.cc_card_holder_placeholder) },
                color = MaterialTheme.colorScheme.onError,
                fontSize = 16.sp,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(start = 24.dp, bottom = 24.dp)
            )

            Icon(
                imageVector = CheapacIcons.Card,
                contentDescription = "credit card provider logo",
                tint = MaterialTheme.colorScheme.onError,
                modifier = Modifier
                    .padding(bottom = 16.dp, end = 24.dp)
                    .size(40.dp)
                    .align(Alignment.BottomEnd)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        CheapacTextField(
            value = uiState.cardHolder,
            onValueChange = { onEvent(PaymentEvent.CardHolderChanged(it)) },
            placeholderStrId = R.string.card_holder,
            leadingIcon = CheapacIcons.ProfileOutlined,
            isError = false,
            errorMessage = "",
        )
        Spacer(modifier = Modifier.height(16.dp))
        CheapacTextField(
            value = uiState.cardNumber,
            onValueChange = { onEvent(PaymentEvent.CardNumberChanged(it)) },
            placeholderStrId = R.string.card_number,
            leadingIcon = CheapacIcons.Card,
            isError = false,
            errorMessage = "",
            keyboardType = KeyboardType.Number,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            CheapacTextField(
                value = uiState.cvc,
                onValueChange = { onEvent(PaymentEvent.CVCChanged(it)) },
                placeholderStrId = R.string.cvc,
                isError = false,
                errorMessage = "",
                keyboardType = KeyboardType.Number,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(10.dp))
            CheapacTextField(
                value = uiState.expirationDate,
                onValueChange = { onEvent(PaymentEvent.ExpirationDateChanged(it)) },
                placeholderStrId = R.string.expiration_date,
                isError = false,
                errorMessage = "",
                keyboardType = KeyboardType.Number,
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = markAsDone,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = stringResource(R.string.continue_next_step))
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun PreviewShippingPage() {
    AppTheme {
        PaymentPage(
            onEvent = {},
            markAsDone = {},
            uiState = PaymentInfo(
                cardHolder = "John Doe",
                cardNumber = "0000 0000 0000 0000",
                cvc = "012",
                expirationDate = "19/07"
            )
        )
    }
}
