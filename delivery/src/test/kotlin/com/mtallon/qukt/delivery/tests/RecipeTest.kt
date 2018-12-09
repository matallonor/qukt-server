package com.mtallon.qukt.delivery.tests

import com.mtallon.qukt.core.entities.Recipe
import com.mtallon.qukt.delivery.App
import com.mtallon.qukt.delivery.rest.api.RecipeDto
import com.mtallon.qukt.usecases.exceptions.NotFoundException
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
import java.lang.Exception

@RunWith(SpringRunner::class)
@SpringBootTest(classes = [(App::class)], webEnvironment = WebEnvironment.RANDOM_PORT)
class RecipeTest {
    @Autowired
    lateinit var restTemplate: TestRestTemplate

    @Test
    fun shouldReturn200_whenRetrievingRecipes() {
        val response = getRecipes<RecipeDto>("chocolate")
        assertEquals(HttpStatus.OK, response.statusCode)
        assertNotNull(response)
    }

    @Test
    fun shouldReturn200AndFilteredRecipes_whenRetrievingRecipesByQuery() {
        val response = getRecipes<RecipeDto>("chocolate")
        val responseDto = response.body!!
        assertEquals(HttpStatus.OK, response.statusCode)
        assertNotNull(response)
        assertTrue(responseDto.isNotEmpty())
        assertNotNull(responseDto[0])
    }

    @Test(expected = Exception::class)
    fun shouldThrowException_whenNotSpecifyingAQuery() {
        val response = getRecipes<RecipeDto>("")
    }

    // That method performs a call to the /recipes endpoint with the given query
    private inline fun <reified T> getRecipes(query: String): ResponseEntity<Array<Recipe>> {
        val url = "/recipes/$query"
        return restTemplate.getForEntity(url, Array<Recipe>::class.java)
    }

}
