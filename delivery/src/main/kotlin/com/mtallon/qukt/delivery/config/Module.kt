package com.mtallon.qukt.delivery.config

import com.mtallon.qukt.dataproviders.db.jpa.repositories.DBRecipeRepository
import com.mtallon.qukt.dataproviders.db.jpa.repositories.ElasticsearchRepository
import com.mtallon.qukt.delivery.rest.imp.RecipeResourceImp
import com.mtallon.qukt.usecases.UseCaseExecutor
import com.mtallon.qukt.usecases.UseCaseExecutorImp
import com.mtallon.qukt.usecases.gateways.RecipeRepository
import com.mtallon.qukt.usecases.recipe.GetRecipesUseCase
import org.elasticsearch.client.Client
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate
import org.springframework.data.elasticsearch.core.ElasticsearchOperations
import java.net.InetAddress
import org.elasticsearch.common.transport.InetSocketTransportAddress
import org.elasticsearch.transport.client.PreBuiltTransportClient
import org.elasticsearch.common.settings.Settings
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter




@EnableWebMvc
@Configuration
class Module {

    @Bean
    fun webMvcConfigurer(): WebMvcConfigurer {
        return object : WebMvcConfigurerAdapter() {
            override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
                registry.addResourceHandler("/public/**")
                        .addResourceLocations("classpath:/public/")
            }
        }
    }

    @Bean
    fun recipesResourceImp(
            useCaseExecutor: UseCaseExecutor,
            getRecipesUseCase: GetRecipesUseCase
    ) = RecipeResourceImp(useCaseExecutor, getRecipesUseCase)

    @Bean
    fun useCaseExecutor() = UseCaseExecutorImp()

    @Bean
    fun getRecipesUseCase(recipeRepository: RecipeRepository) = GetRecipesUseCase(recipeRepository)

    @Bean
    fun recipeRepository(dbRecipeRepository: DBRecipeRepository) = ElasticsearchRepository(dbRecipeRepository)

    @Bean
    fun client(): Client {
        val elasticsearchSettings = Settings.builder()
                .put("client.transport.sniff", false)
                .put("cluster.name", "docker-cluster")
                .build()
        val client = PreBuiltTransportClient(elasticsearchSettings)
        client.addTransportAddress(InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300))
        return client
    }

    @Bean
    fun elasticsearchTemplate(): ElasticsearchOperations {
        return ElasticsearchTemplate(client())
    }
}