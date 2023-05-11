package com.ubaya.a160417097_ubayakuliner.model

import com.google.gson.annotations.SerializedName

data class Category(
    val name: String?
)

data class OpeningHour(
    @SerializedName("Monday")
    val monday: String?,
    @SerializedName("Tuesday")
    val tuesday: String?,
    @SerializedName("Wednesday")
    val wednesday: String?,
    @SerializedName("Thursday")
    val thursday: String?,
    @SerializedName("Friday")
    val friday: String?,
    @SerializedName("Saturday")
    val saturday: String?,
    @SerializedName("Sunday")
    val sunday: String?,
)

data class Review(
    val name: String?,
    val date: String?,
    val rating: String?,
    val comments: String?
)

data class Restaurant(
    val id:String?,
    val name:String?,
    val photo:String?,
    val address: String?,
    val rating: String?,
    @SerializedName("price_range")
    val priceRange: String?,
    val category: ArrayList<Category>,
    val opening_hours: OpeningHour,
    val reviews: ArrayList<Review>
)

data class Item(
    @SerializedName("menu_id")
    val menuID: String?,
    val name: String?,
    val price: String?,
    val photo: String?,
    val description: String?,
)

data class Menu(
    @SerializedName("restaurant_id")
    val restaurantID: String?,
    val items: ArrayList<Item>
)

data class User(
    @SerializedName("user_id")
    val userID: String?,
    val username: String?,
    val password: String?,
    val name: String?,
    val email: String?,
    @SerializedName("no_telp")
    val noTelp: String?
)