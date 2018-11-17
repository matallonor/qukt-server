package com.mtallon.qukt.delivery.rest.imp

import com.mtallon.qukt.delivery.rest.api.*
import com.mtallon.qukt.usecases.UseCaseExecutor
import com.mtallon.qukt.usecases.recipe.GetRecipesUseCase

class RecipeResourceImp(
        private val useCaseExecutor: UseCaseExecutor,
        private val getRecipesUseCase: GetRecipesUseCase
) : RecipesResource {

    override fun getAllRecipes() =
            useCaseExecutor(
                    useCase = getRecipesUseCase,
                    requestDto = Unit,
                    requestConverter = {},
                    responseConverter = { it.map { a -> a.toRecipeDto() } })

}
