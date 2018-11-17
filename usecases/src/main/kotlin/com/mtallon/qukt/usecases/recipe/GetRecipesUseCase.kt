package com.mtallon.qukt.usecases.recipe

import com.mtallon.qukt.core.entities.Recipe
import com.mtallon.qukt.usecases.UseCase
import com.mtallon.qukt.usecases.exceptions.NotFoundException

class GetRecipesUseCase(private val recipeRepository: RecipeRepository) :
        UseCase<Unit, List<Recipe>> {

    override fun execute(request: Unit): List<Recipe> {
        return recipeRepository.getAllRecipes() ?: throw NotFoundException("No recipes found")
    }

    interface RecipeRepository {
        fun getAllRecipes(): List<Recipe>?
    }
}