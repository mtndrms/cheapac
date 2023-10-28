package com.example.cheapac.presentation.common

import androidx.annotation.DrawableRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.cheapac.R

object CheapacIcons {
    val Home: ImageVector = Icons.Default.Home
    val HomeOutlined: ImageVector = Icons.Outlined.Home
    val Profile: ImageVector = Icons.Default.Person
    val ProfileOutlined: ImageVector = Icons.Outlined.Person
    val Cart: ImageVector = Icons.Default.ShoppingCart
    val CartOutlined: ImageVector = Icons.Outlined.ShoppingCart
    val Favorite: ImageVector = Icons.Default.Favorite
    val FavoriteOutlined: ImageVector = Icons.Outlined.FavoriteBorder
    val Category: ImageVector = Icons.Default.List
    val CategoryOutlined: ImageVector = Icons.Outlined.List
    val ArrowBack: ImageVector = Icons.Default.ArrowBack
    val Settings: ImageVector = Icons.Outlined.Settings
    val Help: ImageVector = Icons.Outlined.Info
    val Smartphone: Icon.DrawableResourceIcon = Icon.DrawableResourceIcon(R.drawable.ic_smartphone)
    val Laptop: Icon.DrawableResourceIcon = Icon.DrawableResourceIcon(R.drawable.ic_laptop)
    val Fragrances: Icon.DrawableResourceIcon = Icon.DrawableResourceIcon(R.drawable.ic_fragrances)
    val Skincare: Icon.DrawableResourceIcon = Icon.DrawableResourceIcon(R.drawable.ic_skincare)
    val Groceries: Icon.DrawableResourceIcon = Icon.DrawableResourceIcon(R.drawable.ic_groceries)
    val HomeDecoration: Icon.DrawableResourceIcon = Icon.DrawableResourceIcon(R.drawable.ic_home_decoration)
    val Furniture: Icon.DrawableResourceIcon = Icon.DrawableResourceIcon(R.drawable.ic_furniture)
    val Tops: Icon.DrawableResourceIcon = Icon.DrawableResourceIcon(R.drawable.ic_tops)
    val WomenDresses: Icon.DrawableResourceIcon = Icon.DrawableResourceIcon(R.drawable.ic_womens_dresses)
    val WomenShoes: Icon.DrawableResourceIcon = Icon.DrawableResourceIcon(R.drawable.ic_womens_shoes)
    val MenShirts: Icon.DrawableResourceIcon = Icon.DrawableResourceIcon(R.drawable.ic_mens_shirts)
    val MenShoes: Icon.DrawableResourceIcon = Icon.DrawableResourceIcon(R.drawable.ic_mens_shoes)
    val MenWatches: Icon.DrawableResourceIcon = Icon.DrawableResourceIcon(R.drawable.ic_mens_watches)
    val WomenWatches: Icon.DrawableResourceIcon = Icon.DrawableResourceIcon(R.drawable.ic_womens_watches)
    val WomenBags: Icon.DrawableResourceIcon = Icon.DrawableResourceIcon(R.drawable.ic_womens_bags)
    val WomenJewellery: Icon.DrawableResourceIcon = Icon.DrawableResourceIcon(R.drawable.ic_womens_jewellery)
    val Sunglasses: Icon.DrawableResourceIcon = Icon.DrawableResourceIcon(R.drawable.ic_sunglasses)
    val Automotive: Icon.DrawableResourceIcon = Icon.DrawableResourceIcon(R.drawable.ic_automotive)
    val Motorcycle: Icon.DrawableResourceIcon = Icon.DrawableResourceIcon(R.drawable.ic_motorcycle)
    val Lighting: Icon.DrawableResourceIcon = Icon.DrawableResourceIcon(R.drawable.ic_lighting)
    val RecentlyViewed: Icon.DrawableResourceIcon = Icon.DrawableResourceIcon(R.drawable.ic_recently_viewed)
    val Box: Icon.DrawableResourceIcon = Icon.DrawableResourceIcon(R.drawable.ic_box)
    val History: Icon.DrawableResourceIcon = Icon.DrawableResourceIcon(R.drawable.ic_history)
    val ProductPackage: Icon.DrawableResourceIcon = Icon.DrawableResourceIcon(R.drawable.ic_product_package)
    val Shipment: Icon.DrawableResourceIcon = Icon.DrawableResourceIcon(R.drawable.ic_shipment)
    val Refund: Icon.DrawableResourceIcon = Icon.DrawableResourceIcon(R.drawable.ic_refund)
    val Return: Icon.DrawableResourceIcon = Icon.DrawableResourceIcon(R.drawable.ic_return)
    val Coupon: Icon.DrawableResourceIcon = Icon.DrawableResourceIcon(R.drawable.ic_coupon)
}

sealed class Icon {
    data class ImageVectorIcon(val imageVector: ImageVector) : Icon()
    data class DrawableResourceIcon(@DrawableRes val id: Int) : Icon()
}