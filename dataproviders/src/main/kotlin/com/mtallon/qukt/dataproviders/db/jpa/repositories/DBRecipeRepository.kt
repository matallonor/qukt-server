package com.mtallon.qukt.dataproviders.db.jpa.repositories

import com.mtallon.qukt.dataproviders.db.jpa.entities.RecipeEntity
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository

interface DBRecipeRepository : ElasticsearchRepository<RecipeEntity, String>