package com.example.cheapac.data.mapper

import com.example.cheapac.data.remote.dto.CategoryDto
import com.example.cheapac.domain.model.Category
import com.example.cheapac.presentation.common.CheapacIcons
import com.example.cheapac.utils.capitalize
import okhttp3.internal.canParseAsIpAddress

fun CategoryDto.toCategory(): Category {
    return Category(
        code = slug,
        title = name,
        iconId = pickCategoryIcon(slug)
    )
}

fun List<CategoryDto>.toCategoryList(): List<Category> {
    return this.map { it.toCategory() }
}

fun betterCategoryTitle(category: String): String {
    val capitalized = category.capitalize()
    return if (capitalized.contains("-")) {
        capitalized.replace("-", " ")
    } else {
        capitalized
    }
}

private fun pickCategoryIcon(category: String): Int {
    return when (category) {
        "beauty" -> CheapacIcons.Beauty
        "fragrances" -> CheapacIcons.Fragrances
        "furniture" -> CheapacIcons.Furniture
        "groceries" -> CheapacIcons.Groceries
        "home-decoration" -> CheapacIcons.HomeDecoration
        "kitchen-accessories" -> CheapacIcons.KitchenAccessories
        "laptops" -> CheapacIcons.Laptop
        "mens-shirts" -> CheapacIcons.MenShirts
        "mens-shoes" -> CheapacIcons.MenShoes
        "mens-watches" -> CheapacIcons.MenWatches
        "mobile-accessories" -> CheapacIcons.MobileAccessories
        "motorcycle" -> CheapacIcons.Motorcycle
        "skin-care" -> CheapacIcons.Skincare
        "smartphones" -> CheapacIcons.Smartphone
        "sports-accessories" -> CheapacIcons.SportsAccessories
        "sunglasses" -> CheapacIcons.Sunglasses
        "tablets" -> CheapacIcons.Tablet
        "tops" -> CheapacIcons.Tops
        "vehicle" -> CheapacIcons.Automotive
        "womens-bags" -> CheapacIcons.WomenBags
        "womens-dresses" -> CheapacIcons.WomenDresses
        "womens-jewellery" -> CheapacIcons.WomenJewellery
        "womens-shoes" -> CheapacIcons.WomenShoes
        "womens-watches" -> CheapacIcons.WomenWatches
        else -> CheapacIcons.UnknownCategory
    }.id
}
