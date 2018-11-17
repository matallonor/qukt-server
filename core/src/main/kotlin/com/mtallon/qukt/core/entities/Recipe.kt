package com.mtallon.qukt.core.entities

data class Recipe(
        val title: String,
        val ingredients: List<String>,
        val description: String,
        val type: String?,
        val cookTime: Int?
)