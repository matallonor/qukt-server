package com.mtallon.qukt.core.entities

data class Recipe(
        val id: String,
        val title: String,
        val ingredients: List<String>,
        val description: List<String>,
        val type: String?,
        val cookTime: Int?,
        val image: Image?
)