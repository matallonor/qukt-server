package com.mtallon.qukt.dataproviders.db.jpa.config

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories


@Configuration
@EntityScan(basePackages = ["com.mtallon.qukt.dataproviders.db.jpa.entities"])
@EnableElasticsearchRepositories(basePackages = ["com.mtallon.qukt.dataproviders.db.jpa.repositories"])
class DBConfig