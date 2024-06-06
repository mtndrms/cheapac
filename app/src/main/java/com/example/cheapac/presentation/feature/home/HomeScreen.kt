package com.example.cheapac.presentation.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.cheapac.R
import com.example.cheapac.domain.model.Product
import com.example.cheapac.presentation.common.TopBar
import com.example.cheapac.presentation.component.CategoriesCatalog
import com.example.cheapac.presentation.component.HighlightsCarousel
import com.example.cheapac.presentation.component.HorizontalProducts
import com.example.cheapac.presentation.navigation.TopLevelDestination

@Composable
internal fun HomeRoute(
    navigateToCategories: () -> Unit,
    navigateToCategory: (code: String, title: String) -> Unit,
    navigateToProductDetail: (Int) -> Unit,
    navigateToProfile: (TopLevelDestination) -> Unit,
    navigateToCartScreen: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    HomeScreen(
        navigateToCategories = navigateToCategories,
        navigateToCategory = navigateToCategory,
        navigateToProductDetail = navigateToProductDetail,
        navigateToProfile = navigateToProfile,
        navigateToCartScreen = navigateToCartScreen,
        addToWishlist = viewModel::addProductToWishlist,
        removeProductFromWishlist = viewModel::removeProductFromWishlist,
        addToCart = viewModel::addToCart,
        uiState = uiState,
        modifier = modifier
    )
}

@Composable
private fun HomeScreen(
    navigateToCategories: () -> Unit,
    navigateToCategory: (code: String, title: String) -> Unit,
    navigateToProductDetail: (Int) -> Unit,
    navigateToProfile: (TopLevelDestination) -> Unit,
    navigateToCartScreen: () -> Unit,
    addToWishlist: (Product, String) -> Unit,
    removeProductFromWishlist: (Int) -> Unit,
    addToCart: (Product) -> Unit,
    uiState: HomeUiState,
    modifier: Modifier
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
            .padding(vertical = 10.dp)
            .then(modifier)
    ) {
        Column(
            modifier = Modifier.align(Alignment.TopCenter),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopBar(
                currentScreenTitle = stringResource(id = R.string.app_name),
                navigateToProfile = {
                    navigateToProfile(TopLevelDestination.PROFILE)
                },
                navigateToCartScreen = navigateToCartScreen,
                onTitleClick = { },
                cartSize = uiState.cart.size
            )
            HighlightsCarousel(
                highlights = uiState.highlights,
                autoSwipeDuration = 5000,
                modifier = Modifier.height(128.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))
            CategoriesCatalog(
                categories = uiState.categories,
                navigateToCategories = navigateToCategories,
                navigateToCategory = navigateToCategory,
                modifier = Modifier
            )
            Spacer(modifier = Modifier.height(20.dp))
            HighlightsCarousel(
                highlights = uiState.highlights,
                autoSwipeDuration = 5000,
                modifier = Modifier.height(192.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))
            HorizontalProducts(
                products = uiState.highlights,
                wishlistedProducts = uiState.wishlistedProducts,
                navigateToProductDetail = navigateToProductDetail,
                addToWishlist = addToWishlist,
                removeProductFromWishlist = removeProductFromWishlist,
                addToCart = addToCart,
                modifier = Modifier
            )
        }
    }
}
