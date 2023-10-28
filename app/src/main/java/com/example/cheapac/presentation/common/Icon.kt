package com.example.cheapac.presentation.common

import androidx.annotation.DrawableRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

object CheapacIcons {
    val Home: ImageVector = Icons.Default.Home
    val HomeOutlined: ImageVector = Icons.Outlined.Home
    val Profile: ImageVector = Icons.Default.Person
    val ProfileOutlined: ImageVector = Icons.Outlined.Person
    val Cart: ImageVector = Icons.Default.ShoppingCart
    val CartOutlined: ImageVector = Icons.Outlined.ShoppingCart
    val Favorite: ImageVector = Icons.Default.Favorite
    val FavoriteOutlined: ImageVector = Icons.Outlined.FavoriteBorder
//    val Visible: Icon.DrawableResourceIcon = Icon.DrawableResourceIcon(R.drawable.ic_eye)
//    val NotVisible: Icon.DrawableResourceIcon = Icon.DrawableResourceIcon(R.drawable.ic_eye_outlined)
}

sealed class Icon {
    data class ImageVectorIcon(val imageVector: ImageVector) : Icon()
    data class DrawableResourceIcon(@DrawableRes val id: Int) : Icon()
}