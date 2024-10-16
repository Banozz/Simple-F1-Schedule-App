package com.example.praktikumappf1.bottomnavigations.ui

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Race(
    val name: String,
    val fullName: String,
    val img: Int,
    val date: String,
    val desc: String,
    val web: String
) : Parcelable
