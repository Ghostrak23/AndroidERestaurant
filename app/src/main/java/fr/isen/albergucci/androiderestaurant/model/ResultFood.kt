package fr.isen.albergucci.androiderestaurant.model

import com.google.gson.annotations.SerializedName

data class ResultFood(
    @SerializedName("data" ) var data : ArrayList<Data> = arrayListOf()

) : java.io.Serializable
