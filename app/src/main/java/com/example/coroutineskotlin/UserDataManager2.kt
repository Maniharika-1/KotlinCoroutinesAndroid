package com.example.coroutineskotlin

import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO

class UserDataManager2 {

    suspend fun getTotalUserCount() :Int {

        var count = 0
        lateinit var deferred: Deferred<Int>

        coroutineScope {
            launch(IO) {
                delay(10000)
                count = 70
            }

            deferred = async(IO) {
                delay(2000)
                return@async 20
            }
        }

        return count + deferred.await()
    }
}