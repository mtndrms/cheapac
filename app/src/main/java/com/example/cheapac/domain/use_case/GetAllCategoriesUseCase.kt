package com.example.cheapac.domain.use_case

import com.example.cheapac.data.repository.CategoryRepository
import com.example.cheapac.domain.model.Category
import com.example.cheapac.presentation.common.CheapacIcons
import com.example.cheapac.presentation.common.Icon
import com.example.cheapac.utils.Resource
import com.example.cheapac.utils.capitalize
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllCategoriesUseCase @Inject constructor(private val categoryRepository: CategoryRepository) {
    operator fun invoke(): Flow<Resource<List<Category>>> = flow {
        try {
            emit(Resource.Loading())
            val categories = categoryRepository.getAll().map { toCategory(it) }
            emit(Resource.Success(data = categories))
        } catch (exception: Exception) {
            emit(Resource.Error(message = exception.message ?: ""))
        }
    }

    private fun betterCategoryTitle(category: String): String {
        return when (category) {
            "womens-dresses" -> "Women's dresses"
            "womens-shoes" -> "Women's shoes"
            "mens-shirts" -> "Men's shirts"
            "mens-shoes" -> "Men's shoes"
            "mens-watches" -> "Men's watches"
            "womens-watches" -> "Women's watches"
            "womens-bags" -> "Women's bags"
            "womens-jewellery" -> "Women's jewellery"
            else -> category.capitalize()
        }
    }

    private fun pickCategoryIcon(category: String): Int {
        return when (category) {
            "smartphones" -> CheapacIcons.Smartphone
            "laptops" -> CheapacIcons.Laptop
            "fragrances" -> CheapacIcons.Fragrances
            "skincare" -> CheapacIcons.Skincare
            "groceries" -> CheapacIcons.Groceries
            "home-decoration" -> CheapacIcons.HomeDecoration
            "furniture" -> CheapacIcons.Furniture
            "tops" -> CheapacIcons.Tops
            "womens-dresses" -> CheapacIcons.WomenDresses
            "womens-shoes" -> CheapacIcons.WomenShoes
            "mens-shirts" -> CheapacIcons.MenShirts
            "mens-shoes" -> CheapacIcons.MenShoes
            "mens-watches" -> CheapacIcons.MenWatches
            "womens-watches" -> CheapacIcons.WomenWatches
            "womens-bags" -> CheapacIcons.WomenBags
            "womens-jewellery" -> CheapacIcons.WomenJewellery
            "sunglasses" -> CheapacIcons.Sunglasses
            "automotive" -> CheapacIcons.Automotive
            "motorcycle" -> CheapacIcons.Motorcycle
            "lighting" -> CheapacIcons.Lighting
            else -> CheapacIcons.Smartphone
        }.id
    }

    private fun toCategory(title: String): Category {
        return Category(title = betterCategoryTitle(title), iconId = pickCategoryIcon(title))
    }
}