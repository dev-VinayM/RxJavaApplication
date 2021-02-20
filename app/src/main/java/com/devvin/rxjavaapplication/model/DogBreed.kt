package com.vmcorp.foodisu.model

import com.google.gson.annotations.SerializedName

data class DogBreed(
    @SerializedName(value = "name")
    val name : String,
    @SerializedName(value = "breed_group")
    val breedGroup : String,
    val height : Height,
    @SerializedName(value = "id")
    val dogId : String,
    @SerializedName(value = "url")
    val imageUrl : String
) {
}