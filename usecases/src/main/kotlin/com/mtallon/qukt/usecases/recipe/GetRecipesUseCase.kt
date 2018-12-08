package com.mtallon.qukt.usecases.recipe

import com.mtallon.qukt.core.entities.Recipe
import com.mtallon.qukt.usecases.UseCase
import com.mtallon.qukt.usecases.exceptions.NotFoundException

class GetRecipesUseCase(private val recipeRepository: RecipeRepository) :
        UseCase<String, List<Recipe>> {

    override fun execute(query: String): List<Recipe> {
        return recipeRepository.getAllRecipes(query) ?: throw NotFoundException("No recipes found")
    }

    interface RecipeRepository {
        fun getAllRecipes(query: String): List<Recipe>?
    }
}