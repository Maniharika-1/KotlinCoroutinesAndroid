package com.example.coroutineskotlin

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class UserDataManager {

    suspend fun getTotalUserCount() : Int {

        var count = 0

        CoroutineScope(IO).launch {
            delay(10000)
             count = 50
        }

        return count
    }
}