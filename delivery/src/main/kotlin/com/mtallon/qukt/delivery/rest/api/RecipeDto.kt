package com.mtallon.qukt.delivery.rest.api

import com.mtallon.qukt.core.entities.Recipe

data class RecipeDto(
        val title: String,
        val ingredients: List<String>,
        val description: String,
        val type: String? = null,
        val cookTime: Int? = 0
)

// Mappers
fun Recipe.toRecipeDto() =
        RecipeDto(

                title = this.title,
                ingredients = this.ingredients,
                description = this.description,
                type = this.type,
                cookTime = this.cookTime
)

fun RecipeDto.toRecipe() =
        Recipe(
                title = this.title,
                ingredients = this.ingredients,
                description = this.description,
                type = this.type,
                cookTime = this.cookTime
        )
