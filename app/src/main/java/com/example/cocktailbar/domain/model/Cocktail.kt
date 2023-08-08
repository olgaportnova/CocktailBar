package com.example.cocktailbar.domain.model

import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*

data class Cocktail(

        val image:String?,
        val name:String,
        val discrip: String?,
        val recep: String?,
        val ing: List <String>

): Serializable
