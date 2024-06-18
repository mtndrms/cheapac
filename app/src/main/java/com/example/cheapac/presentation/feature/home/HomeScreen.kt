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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.cheapac.R
import com.example.cheapac.presentation.common.CheapacIcons
import com.example.cheapac.presentation.component.CategoriesCatalog
import com.example.cheapac.presentation.component.HighlightsCarousel
import com.example.cheapac.presentation.component.HorizontalProducts
import com.example.cheapac.presentation.component.SearchBar
import com.example.cheapac.presentation.component.top_bar.TopBar
import com.example.cheapac.presentation.component.top_bar.TopBarButtonOpts
import com.example.cheapac.presentation.navigation.TopLevelDestination

@Composable
internal fun HomeRoute(
    navigateToCategories: () -> Unit,
    navigateToCategory: (code: String, title: String) -> Unit,
    navigateToProductDetail: (Int) -> Unit,
    navigateToProfile: (TopLevelDestination) -> Unit,
    navigateToCartScreen: () -> Unit,
    navigateToSearchResultScreen: (String) -> Unit,
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
        navigateToSearchResultScreen = navigateToSearchResultScreen,
        onEvent = viewModel::onEvent,
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
    navigateToSearchResultScreen: (String) -> Unit,
    onEvent: (HomeEvent) -> Unit,
    uiState: HomeUiState,
    modifier: Modifier
) {
    LaunchedEffect(key1 = true) {
        onEvent(HomeEvent.InitialFetch)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
            .padding(bottom = 10.dp)
            .then(modifier)
    ) {
        Column(
            modifier = Modifier.align(Alignment.TopCenter),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopBar(
                title = stringResource(id = R.string.app_name),
                primaryButtonOpts = TopBarButtonOpts(
                    icon = CheapacIcons.CartOutlined,
                    onClick = navigateToCartScreen,
                    badgeLabel = uiState.cart.size.toString(),
                    shouldShowBadge = uiState.cart.isNotEmpty(),
                ),
                secondaryButtonOpts = TopBarButtonOpts(
                    icon = CheapacIcons.ProfileOutlined,
                    onClick = { navigateToProfile(TopLevelDestination.PROFILE) }
                ),
            )
            SearchBar(
                onSearchClick = navigateToSearchResultScreen,
                modifier = Modifier.padding(horizontal = 20.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))
            HighlightsCarousel(
                highlights = uiState.mainHighlights,
                navigateToProductDetail = navigateToProductDetail,
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
                highlights = uiState.subHighlights,
                navigateToProductDetail = navigateToProductDetail,
                modifier = Modifier.height(192.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))
            HorizontalProducts(
                products = uiState.mainHighlights,
                wishlistedProducts = uiState.wishlistedProducts,
                navigateToProductDetail = navigateToProductDetail,
                addProductToWishlist = { product, note ->
                    onEvent(
                        HomeEvent.AddProductToWishlist(
                            product = product,
                            note = note
                        )
                    )
                },
                removeProductFromWishlist = { id ->
                    onEvent(HomeEvent.RemoveProductFromWishlist(id = id))
                },
                addProductToCart = { product ->
                    onEvent(HomeEvent.AddProductToCart(product = product))
                },
                modifier = Modifier
            )
        }
    }
}
