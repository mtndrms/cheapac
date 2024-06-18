package com.example.cheapac.presentation.feature.profile

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.cheapac.R
import com.example.cheapac.presentation.common.CheapacIcons
import com.example.cheapac.presentation.component.top_bar.TopBar
import com.example.cheapac.presentation.component.top_bar.TopBarButtonOpts
import com.example.cheapac.presentation.feature.profile.component.UserInfoCard

@Composable
internal fun ProfileRoute(
    navigateToWishlistScreen: () -> Unit,
    navigateToPurchaseHistoryScreen: () -> Unit,
    navigateToRecentlyViewedScreen: () -> Unit,
    goBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    ProfileScreen(
        navigateToWishlistScreen = navigateToWishlistScreen,
        navigateToPurchaseHistoryScreen = navigateToPurchaseHistoryScreen,
        navigateToRecentlyViewedScreen = navigateToRecentlyViewedScreen,
        goBack = goBack
    )
}

@Composable
private fun ProfileScreen(
    navigateToWishlistScreen: () -> Unit,
    navigateToPurchaseHistoryScreen: () -> Unit,
    navigateToRecentlyViewedScreen: () -> Unit,
    goBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        TopBar(
            title = stringResource(id = R.string.profile),
            navigationButtonOpts = TopBarButtonOpts(
                icon = CheapacIcons.ArrowBack,
                onClick = goBack
            )
        )
        Spacer(modifier = Modifier.height(5.dp))
        UserInfoCard(name = "John Doe", email = "johndoe@example.com")
        Spacer(modifier = Modifier.height(5.dp))
        Column {
            OptionRow(
                title = stringResource(id = R.string.wishlist),
                imageVector = CheapacIcons.FavoriteOutlined,
                onClick = { navigateToWishlistScreen() }
            )
            OptionRow(
                title = stringResource(R.string.your_coupons),
                iconResId = CheapacIcons.Coupon.id,
                onClick = {}
            )
            OptionRow(
                title = stringResource(id = R.string.purchase_history),
                imageVector = CheapacIcons.History,
                onClick = { navigateToPurchaseHistoryScreen() }
            )
            OptionRow(
                title = stringResource(id = R.string.recently_viewed),
                iconResId = CheapacIcons.RecentlyViewed.id,
                onClick = { navigateToRecentlyViewedScreen() }
            )
            OptionRow(
                title = stringResource(R.string.shipments),
                iconResId = CheapacIcons.Shipment.id,
                onClick = {}
            )
            OptionRow(
                title = stringResource(R.string.refund_return_requests),
                iconResId = CheapacIcons.Return.id,
                onClick = {}
            )
            OptionRow(
                title = stringResource(R.string.settings),
                imageVector = CheapacIcons.Settings,
                onClick = {}
            )
            OptionRow(
                title = stringResource(R.string.help),
                imageVector = CheapacIcons.Help,
                onClick = {}
            )
        }
    }
}

@Composable
private fun OptionRow(
    title: String,
    @DrawableRes iconResId: Int? = null,
    imageVector: ImageVector? = null,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .clickable { onClick() }
            .padding(horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        iconResId?.let { resId ->
            Icon(
                painter = painterResource(id = resId),
                contentDescription = title,
                modifier = Modifier.size(32.dp)
            )
        }

        imageVector?.let { vector ->
            Icon(
                imageVector = vector,
                contentDescription = title,
                modifier = Modifier.size(32.dp)
            )
        }
        Spacer(modifier = Modifier.width(20.dp))
        Text(
            text = title,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
