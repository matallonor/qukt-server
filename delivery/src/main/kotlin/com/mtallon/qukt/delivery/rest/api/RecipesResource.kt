package com.mtallon.qukt.delivery.rest.api

import org.springframework.web.bind.annotation.*
import java.util.concurrent.CompletionStage

@RestController
@RequestMapping("/recipes")
interface RecipesResource {

    @CrossOrigin()
    @GetMapping("/")
    fun getAllRecipes(): CompletionStage<List<RecipeDto>>
}
