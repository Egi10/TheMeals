package id.buaja.themeal.api

import com.google.gson.annotations.SerializedName
import id.buaja.themeal.model.CategoriesItem

data class Response(
	@field:SerializedName("categories")
	val categories: List<CategoriesItem>?
)