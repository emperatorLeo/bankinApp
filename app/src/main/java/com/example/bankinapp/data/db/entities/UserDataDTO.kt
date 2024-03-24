package com.example.bankinapp.data.db.entities

data class UserDataDTO(
    val name: String,
    val lastName: String,
    val password: String,
    val imageUrl: String,
    val movements: ArrayList<Movements>
)

data class Movements(val amount: Long, val date: String, val description: String)

fun fromHashMapToMovements(arrayList: ArrayList<HashMap<String, Any>>): ArrayList<Movements> {
    val array = arrayListOf<Movements>()
    for (raw in arrayList) {
        array.add(
            Movements(
                amount = raw["Amount"] as Long,
                date = raw["Date"] as String,
                description = raw["Description"] as String
            )
        )
    }
    return array
}
