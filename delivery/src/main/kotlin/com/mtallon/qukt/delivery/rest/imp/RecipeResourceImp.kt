package com.mtallon.qukt.delivery.rest.imp

import com.mtallon.qukt.delivery.rest.api.*
import com.mtallon.qukt.usecases.UseCaseExecutor
import com.mtallon.qukt.usecases.recipe.GetRecipesUseCase
import org.springframework.web.bind.annotation.PathVariable

class RecipeResourceImp(
        private val useCaseExecutor: UseCaseExecutor,
        private val getRecipesUseCase: GetRecipesUseCase
) : RecipesResource {

    override fun getAllRecipes(@PathVariable("query") query: String) =
            useCaseExecutor(
                    useCase = getRecipesUseCase,
                    requestDto = query,
                    requestConverter = { query },
                    responseConverter = { it.map { a -> a.toRecipeDto() } })

}
