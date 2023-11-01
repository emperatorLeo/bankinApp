package com.example.bankinapp.data.db.entities



data class UserDataEntity(val name: String, val lastName: String, val password:String, val movements: ArrayList<Movements>)

data class Movements(val amount: Int, val date: String, val description: String)
