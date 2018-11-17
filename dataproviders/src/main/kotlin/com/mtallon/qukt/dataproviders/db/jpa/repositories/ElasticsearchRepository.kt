package com.mtallon.qukt.dataproviders.db.jpa.repositories

import com.mtallon.qukt.core.entities.Recipe
import com.mtallon.qukt.dataproviders.db.jpa.entities.toRecipe
import com.mtallon.qukt.usecases.gateways.RecipeRepository

open class ElasticsearchRepository(private val dbRecipeRepository: DBRecipeRepository) : RecipeRepository {

    override fun getAllRecipes(): List<Recipe>? {
        return dbRecipeRepository.findAll().map { it.toRecipe() }
    }

}