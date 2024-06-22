package com.example.cheapac.presentation.component

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.tooling.preview.PreviewFontScale
import com.example.cheapac.R

@Composable
fun CheapacTextField(
    value: String,
    onValueChange: (String) -> Unit,
    @StringRes placeholderStrId: Int,
    isError: Boolean,
    errorMessage: String,
    modifier: Modifier = Modifier,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    @DrawableRes leadingIconResId: Int? = null,
    @DrawableRes trailingIconResId: Int? = null,
    keyboardType: KeyboardType? = null,
) {
    OutlinedTextField(
        value = value,
        onValueChange = {
            onValueChange(it)
        },
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType ?: KeyboardType.Text),
        singleLine = true,
        placeholder = {
            Text(text = stringResource(placeholderStrId))
        },
        leadingIcon = if (leadingIcon != null) {
            {
                Icon(
                    imageVector = leadingIcon,
                    contentDescription = stringResource(R.string.app_name),
                    tint = if (isError) {
                        MaterialTheme.colorScheme.error
                    } else {
                        MaterialTheme.colorScheme.onBackground
                    }
                )
            }
        } else if (leadingIconResId != null) {
            {
                Icon(
                    painter = painterResource(id = leadingIconResId),
                    contentDescription = stringResource(R.string.app_name),
                    tint = if (isError) {
                        MaterialTheme.colorScheme.error
                    } else {
                        MaterialTheme.colorScheme.onBackground
                    }
                )
            }
        } else null,
        trailingIcon = if (value.isNotEmpty()) {
            if (trailingIcon != null) {
                {
                    Icon(
                        imageVector = trailingIcon,
                        contentDescription = stringResource(R.string.app_name),
                        tint = if (isError) {
                            MaterialTheme.colorScheme.error
                        } else {
                            MaterialTheme.colorScheme.onBackground
                        }
                    )
                }
            } else if (trailingIconResId != null) {
                {
                    Icon(
                        painter = painterResource(id = trailingIconResId),
                        contentDescription = stringResource(R.string.app_name),
                        tint = if (isError) {
                            MaterialTheme.colorScheme.error
                        } else {
                            MaterialTheme.colorScheme.onBackground
                        }
                    )
                }
            } else null
        } else null,
        isError = isError,
        supportingText = if (isError) {
            {
                Text(text = errorMessage)
            }
        } else null,
        colors = TextFieldDefaults.colors(
            unfocusedIndicatorColor = MaterialTheme.colorScheme.surfaceVariant,
            unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
            focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
            errorPlaceholderColor = MaterialTheme.colorScheme.error
        ),
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier)
    )
}

@Composable
@PreviewFontScale
private fun PreviewCheapacTextField() {
    CheapacTextField(
        value = "John Doe",
        onValueChange = { _ -> },
        placeholderStrId = R.string.address,
        isError = false,
        errorMessage = ""
    )
}
