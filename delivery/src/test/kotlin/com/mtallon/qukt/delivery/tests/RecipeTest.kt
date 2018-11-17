package com.mtallon.qukt.delivery.tests

import com.mtallon.qukt.delivery.App
import com.mtallon.qukt.delivery.rest.api.RecipeDto
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest(classes = [(App::class)], webEnvironment = WebEnvironment.RANDOM_PORT)
class RecipeTest {
    @Autowired
    lateinit var restTemplate: TestRestTemplate

    // TESTS
    @Test
    fun shouldReturn200AndTheRecipes_whenRetrievingAllRecipes() {
        val response = getAllRecipes<RecipeDto>()
        val responseDto = response.body!!
        assertEquals(HttpStatus.OK, response.statusCode)
        assertNotNull(responseDto.get(0))
    }


    // METHODS
    private inline fun <reified T> getAllRecipes(): ResponseEntity<List<T>> {
        return restTemplate.getForEntity(   "/recipes/", object: ParameterizedTypeReference<List<T>>(){})
    }

    private inline fun <reified T> createRecipe(
        title: String,
        ingredients: List<String>,
        description: String
    ): ResponseEntity<T> {
        val entity = HttpEntity(RecipeDto(title = title, ingredients = ingredients, description = description))
        return restTemplate.exchange("/recipes/", HttpMethod.POST, entity, T::class.java)
    }

}
