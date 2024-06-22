package com.example.cheapac.presentation.feature.cart.checkout.review

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewFontScale
import androidx.compose.ui.unit.dp
import com.example.cheapac.R
import com.example.cheapac.presentation.feature.cart.checkout.payment.PaymentInfo
import com.example.cheapac.presentation.feature.cart.checkout.shipping.ShippingInfo
import com.example.cheapac.presentation.theme.AppTheme

@Composable
fun ReviewPage(
    shippingInfo: ShippingInfo,
    paymentInfo: PaymentInfo,
    proceed: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.full_name),
            style = MaterialTheme.typography.titleSmall
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = shippingInfo.fullName,
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(id = R.string.phone_number),
            style = MaterialTheme.typography.titleSmall
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = shippingInfo.phoneNumber,
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(id = R.string.address),
            style = MaterialTheme.typography.titleSmall
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "${shippingInfo.address}, ${shippingInfo.country}",
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(id = R.string.card_number),
            style = MaterialTheme.typography.titleSmall
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = paymentInfo.cardNumber,
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(id = R.string.expiration_date),
            style = MaterialTheme.typography.titleSmall
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = paymentInfo.expirationDate,
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(id = R.string.cvc),
            style = MaterialTheme.typography.titleSmall
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = paymentInfo.cvc,
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = proceed, modifier = Modifier.fillMaxWidth()) {
            Text(text = stringResource(R.string.proceed))
        }
    }
}

@Composable
@Preview(showBackground = true)
@PreviewFontScale
private fun PreviewReviewPage() {
    AppTheme {
        ReviewPage(
            proceed = {},
            shippingInfo = ShippingInfo(
                firstName = "John",
                lastName = "Doe",
                phoneNumber = "+10123456",
                address = "The Oval Office, White House, Washington DC",
                country = "United States",
            ),
            paymentInfo = PaymentInfo(
                cardNumber = "0000 0000 0000 0000",
                expirationDate = "19/07",
                cvc = "012",
            )
        )
    }
}
