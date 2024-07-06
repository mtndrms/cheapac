package com.example.cheapac.presentation.component.top_bar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.cheapac.utils.Dimens
import com.example.cheapac.utils.capitalize

/**
 * A Composable function that represents a top app bar.
 *
 * @param title The title to display in the top app bar.
 * @param titleLocation The location of the title in relation to other elements (default: TopBarTitleLocation.START).
 * @param onTitleClick Lambda function to be executed when the title is clicked.
 * @param navigationButtonOpts Options for the navigation button on the left side of the top bar.
 * @param primaryButtonOpts Options for the primary action button on the right side of the top bar.
 * @param secondaryButtonOpts Options for the secondary action button on the right side of the top bar.
 *
 * @see TopBarTitleLocation
 * @see TopBarButtonOpts
 */
@Composable
fun TopBar(
    title: String,
    titleLocation: TopBarTitleLocation = TopBarTitleLocation.START,
    onTitleClick: () -> Unit = {},
    navigationButtonOpts: TopBarButtonOpts? = null,
    primaryButtonOpts: TopBarButtonOpts? = null,
    secondaryButtonOpts: TopBarButtonOpts? = null,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(Dimens.TOP_BAR_HEIGHT)
            .background(MaterialTheme.colorScheme.background)
    ) {
        navigationButtonOpts?.let { opts ->
            TopBarButton(opts = opts)
        }

        if (primaryButtonOpts != null || secondaryButtonOpts != null) {
            if (titleLocation != TopBarTitleLocation.START) {
                if (navigationButtonOpts == null) {
                    Spacer(modifier = Modifier.width(48.dp))
                }

                if (primaryButtonOpts != null && secondaryButtonOpts != null) {
                    Spacer(modifier = Modifier.width(48.dp))
                }
            }
        }

        Title(
            title = title,
            titleLocation = titleLocation,
            isNavigationButtonVisible = navigationButtonOpts != null,
            isPrimaryOrSecondaryButtonVisible = primaryButtonOpts != null || secondaryButtonOpts != null,
            onClick = onTitleClick,
            modifier = Modifier.weight(1f)
        )

        if (navigationButtonOpts != null && primaryButtonOpts == null && secondaryButtonOpts == null) {
            if (titleLocation != TopBarTitleLocation.END) {
                Spacer(modifier = Modifier.width(48.dp))
            }
        }

        secondaryButtonOpts?.let {
            if (it.shouldHideButton && titleLocation == TopBarTitleLocation.CENTER) {
                Spacer(modifier = Modifier.width(48.dp))
            }
        }

        primaryButtonOpts?.let { opts ->
            if (!opts.shouldHideButton) {
                TopBarButton(opts = opts)
            } else {
                if (titleLocation == TopBarTitleLocation.CENTER) {
                    Spacer(modifier = Modifier.width(48.dp))
                }
            }
        }

        secondaryButtonOpts?.let { opts ->
            if (!opts.shouldHideButton) {
                TopBarButton(opts = opts)
            }
        }
    }
}

@Composable
private fun Title(
    title: String,
    titleLocation: TopBarTitleLocation,
    isNavigationButtonVisible: Boolean,
    isPrimaryOrSecondaryButtonVisible: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Text(
        text = title.capitalize(),
        textAlign = when (titleLocation) {
            TopBarTitleLocation.START -> TextAlign.Start
            TopBarTitleLocation.CENTER -> TextAlign.Center
            TopBarTitleLocation.END -> TextAlign.End
        },
        style = MaterialTheme.typography.titleLarge,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier
            .then(modifier)
            .padding(
                start = if (!isNavigationButtonVisible && titleLocation == TopBarTitleLocation.START) 16.dp else 0.dp,
                end = if (!isPrimaryOrSecondaryButtonVisible && titleLocation == TopBarTitleLocation.END) 16.dp else 0.dp
            )
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) { onClick() }
    )
}

@Composable
private fun TopBarButton(opts: TopBarButtonOpts) {
    if (!opts.shouldHideButton) {
        Box {
            IconButton(onClick = opts.onClick) {
                when {
                    opts.icon != null -> {
                        Icon(
                            imageVector = opts.icon,
                            contentDescription = "primary button",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }

                    opts.iconID != null -> {
                        Icon(
                            painter = painterResource(id = opts.iconID),
                            contentDescription = "secondary button",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }

            if (opts.shouldShowBadge) {
                Text(
                    text = opts.badgeLabel.orEmpty(),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.BottomStart)
                )
            }
        }
    } else {
        Spacer(modifier = Modifier.width(48.dp))
    }
}
