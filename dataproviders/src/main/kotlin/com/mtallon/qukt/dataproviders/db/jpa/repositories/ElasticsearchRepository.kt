package com.mtallon.qukt.dataproviders.db.jpa.repositories

import com.mtallon.qukt.core.entities.Recipe
import com.mtallon.qukt.dataproviders.db.jpa.entities.toRecipe
import com.mtallon.qukt.usecases.exceptions.NotFoundException
import com.mtallon.qukt.usecases.gateways.RecipeRepository
import org.elasticsearch.index.query.QueryBuilders
import org.springframework.data.domain.PageRequest
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder


open class ElasticsearchRepository(private val dbRecipeRepository: DBRecipeRepository) : RecipeRepository {

    @Throws()
    override fun getAllRecipes(query: String):  List<Recipe>? {
//        A filer for a field based on several terms matching on any of them.
        val termsLookupFilterBuilder = QueryBuilders.termsQuery("ingredients", query.split(",").map { x -> x.trim() })
        val queryBuilder = QueryBuilders.boolQuery().must(QueryBuilders.matchAllQuery()).must(termsLookupFilterBuilder)

        val searchQuery = NativeSearchQueryBuilder()
                .withQuery(queryBuilder)
                .withPageable(PageRequest.of(0, 10))
                .build()

        if (dbRecipeRepository.search(searchQuery).totalElements <= 0L) {
            throw NotFoundException("No results for query $query")
        }

        return dbRecipeRepository.search(searchQuery).content.map { it.toRecipe() }

    }

}