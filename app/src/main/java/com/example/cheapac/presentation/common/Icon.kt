package com.example.cheapac.presentation.common

import androidx.annotation.DrawableRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Person
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
    val Smartphone: Icon.DrawableResourceIcon = Icon.DrawableResourceIcon(R.drawable.smartphone)
    val Laptop: Icon.DrawableResourceIcon = Icon.DrawableResourceIcon(R.drawable.laptop)
    val Fragrances: Icon.DrawableResourceIcon = Icon.DrawableResourceIcon(R.drawable.fragrances)
    val Skincare: Icon.DrawableResourceIcon = Icon.DrawableResourceIcon(R.drawable.skincare)
    val Groceries: Icon.DrawableResourceIcon = Icon.DrawableResourceIcon(R.drawable.groceries)
    val HomeDecoration: Icon.DrawableResourceIcon = Icon.DrawableResourceIcon(R.drawable.home_decoration)
    val Furniture: Icon.DrawableResourceIcon = Icon.DrawableResourceIcon(R.drawable.furniture)
    val Tops: Icon.DrawableResourceIcon = Icon.DrawableResourceIcon(R.drawable.tops)
    val WomenDresses: Icon.DrawableResourceIcon = Icon.DrawableResourceIcon(R.drawable.womens_dresses)
    val WomenShoes: Icon.DrawableResourceIcon = Icon.DrawableResourceIcon(R.drawable.womens_shoes)
    val MenShirts: Icon.DrawableResourceIcon = Icon.DrawableResourceIcon(R.drawable.mens_shirts)
    val MenShoes: Icon.DrawableResourceIcon = Icon.DrawableResourceIcon(R.drawable.mens_shoes)
    val MenWatches: Icon.DrawableResourceIcon = Icon.DrawableResourceIcon(R.drawable.mens_watches)
    val WomenWatches: Icon.DrawableResourceIcon = Icon.DrawableResourceIcon(R.drawable.womens_watches)
    val WomenBags: Icon.DrawableResourceIcon = Icon.DrawableResourceIcon(R.drawable.womens_bags)
    val WomenJewellery: Icon.DrawableResourceIcon = Icon.DrawableResourceIcon(R.drawable.womens_jewellery)
    val Sunglasses: Icon.DrawableResourceIcon = Icon.DrawableResourceIcon(R.drawable.sunglasses)
    val Automotive: Icon.DrawableResourceIcon = Icon.DrawableResourceIcon(R.drawable.automotive)
    val Motorcycle: Icon.DrawableResourceIcon = Icon.DrawableResourceIcon(R.drawable.motorcycle)
    val Lighting: Icon.DrawableResourceIcon = Icon.DrawableResourceIcon(R.drawable.lighting)
}

sealed class Icon {
    data class ImageVectorIcon(val imageVector: ImageVector) : Icon()
    data class DrawableResourceIcon(@DrawableRes val id: Int) : Icon()
}