package com.mtallon.qukt.dataproviders.db.jpa.entities

import com.mtallon.qukt.core.entities.Image
import com.mtallon.qukt.core.entities.Recipe
import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.Document
import org.springframework.data.elasticsearch.annotations.Field
import org.springframework.data.elasticsearch.annotations.FieldType


@Document(indexName = "qukt", type = "recipes")
data class RecipeEntity(

    @Id
    val id: String = "",
    val title: String = "",
    @Field(type = FieldType.text)
    val ingredients: List<String> = emptyList(),
    val description: List<String> = emptyList(),
    val type: String? = "",
    val cookTime: Int? = -1,
    @Field(type = FieldType.Object)
    val image: Image? = null

) {}

fun RecipeEntity() = {}

// Mappers
fun RecipeEntity.toRecipe() =
        Recipe(
                id = this.id,
                title = this.title,
                ingredients = this.ingredients,
                description = this.description,
                type = this.type,
                cookTime = this.cookTime,
                image = this.image
        )

fun Recipe.toRecipeEntity() =
        RecipeEntity(
                id = this.id,
                title = this.title,
                ingredients = this.ingredients,
                description = this.description,
                type = this.type,
                cookTime = this.cookTime,
                image = this.image
        )
