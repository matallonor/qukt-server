package com.mtallon.qukt.delivery.rest.api

import com.mtallon.qukt.core.entities.Image
import com.mtallon.qukt.core.entities.Recipe

data class RecipeDto(
        val id: String,
        val title: String,
        val ingredients: List<String>,
        val description: List<String>,
        val type: String? = null,
        val cookTime: Int? = 0,
        val image: Image? = null
)

// Mappers
fun Recipe.toRecipeDto() =
        RecipeDto(
                id = this.id,
                title = this.title,
                ingredients = this.ingredients,
                description = this.description,
                type = this.type,
                cookTime = this.cookTime,
                image = this.image
)

fun RecipeDto.toRecipe() =
        Recipe(
                id = this.id,
                title = this.title,
                ingredients = this.ingredients,
                description = this.description,
                type = this.type,
                cookTime = this.cookTime,
                image = this.image
        )
