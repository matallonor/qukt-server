package com.mtallon.qukt.dataproviders.db.jpa.entities

import com.mtallon.qukt.core.entities.Recipe
import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.Document


@Document(indexName = "qukt", type = "recipes")
data class RecipeEntity(

    @Id
    val title: String = "",
    val ingredients: List<String> = emptyList(),
    val description: String = "",
    val type: String? = "",
    val cookTime: Int? = -1

) {}

// Mappers
fun RecipeEntity.toRecipe() =
        Recipe(
                title = this.title,
                ingredients = this.ingredients,
                description = this.description,
                type = this.type,
                cookTime = this.cookTime
        )

fun Recipe.toRecipeEntity() =
        RecipeEntity(
                title = this.title,
                ingredients = this.ingredients,
                description = this.description,
                type = this.type,
                cookTime = this.cookTime
        )
