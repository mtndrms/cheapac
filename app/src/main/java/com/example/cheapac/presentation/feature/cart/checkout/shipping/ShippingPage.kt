package com.example.cheapac.presentation.feature.cart.checkout.shipping

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cheapac.R
import com.example.cheapac.presentation.common.CheapacIcons
import com.example.cheapac.presentation.component.CheapacTextField
import com.example.cheapac.presentation.theme.AppTheme
import com.example.cheapac.utils.Constants

@Composable
fun ShippingPage(onEvent: (ShippingEvent) -> Unit, markAsDone: () -> Unit, uiState: ShippingInfo) {
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        CheapacTextField(
            value = uiState.firstName,
            onValueChange = { onEvent(ShippingEvent.FirstNameChanged(it)) },
            placeholderStrId = R.string.first_name,
            isError = false,
            errorMessage = "",
        )
        Spacer(modifier = Modifier.height(16.dp))
        CheapacTextField(
            value = uiState.lastName,
            onValueChange = { onEvent(ShippingEvent.LastNameChanged(it)) },
            placeholderStrId = R.string.last_name,
            isError = false,
            errorMessage = "",
        )
        Spacer(modifier = Modifier.height(16.dp))
        CheapacTextField(
            value = uiState.phoneNumber,
            onValueChange = { onEvent(ShippingEvent.PhoneNumberChanged(it)) },
            placeholderStrId = R.string.phone_number,
            leadingIcon = CheapacIcons.Phone,
            isError = false,
            errorMessage = "",
            keyboardType = KeyboardType.Phone,
        )
        Spacer(modifier = Modifier.height(16.dp))
        CheapacTextField(
            value = uiState.address,
            onValueChange = { onEvent(ShippingEvent.AddressChanged(it)) },
            placeholderStrId = R.string.address,
            leadingIcon = CheapacIcons.Address,
            isError = false,
            errorMessage = "",
        )
        Spacer(modifier = Modifier.height(16.dp))
        CountryPicker(onEvent)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = markAsDone, modifier = Modifier.fillMaxWidth()) {
            Text(text = stringResource(R.string.continue_next_step))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CountryPicker(onEvent: (ShippingEvent) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    var text by remember { mutableStateOf(Constants.COUNTRIES[0]) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = it },
    ) {
        OutlinedTextField(
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth(),
            value = text,
            onValueChange = {},
            readOnly = true,
            singleLine = true,
            placeholder = { Text(stringResource(R.string.country)) },
            leadingIcon = { Icon(imageVector = CheapacIcons.Country, contentDescription = "") },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            colors = TextFieldDefaults.colors(
                unfocusedIndicatorColor = MaterialTheme.colorScheme.surfaceVariant,
                unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                errorPlaceholderColor = MaterialTheme.colorScheme.error
            ),
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.exposedDropdownSize()
        ) {
            Constants.COUNTRIES.forEach { option ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = option,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    },
                    onClick = {
                        onEvent(ShippingEvent.CountrySelected(option))
                        text = option
                        expanded = false
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun PreviewShippingPage() {
    AppTheme {
        ShippingPage(onEvent = {}, markAsDone = {}, uiState = ShippingInfo())
    }
}
