package com.example.coroutineskotlin

import kotlinx.coroutines.delay

class UserRepository {

    suspend fun getUsers() : List<User> {
        delay(10000)

        val users: List<User> = listOf(
            User(1, "Mani"),
            User(2, "Harika"),
            User(3, "Teja"),
            User(4, "Maniharika")
        )

        return users
    }
}