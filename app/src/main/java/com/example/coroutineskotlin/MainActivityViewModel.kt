package com.example.coroutineskotlin

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*

class MainActivityViewModel : ViewModel() {

    //private val myJob = Job()

    //private val myScope = CoroutineScope(Dispatchers.IO + myJob) //myJob will allow us to control all the coroutines
    //launched in this scope

    private var userRepository = UserRepository()
    var usersList: MutableLiveData<List<User>> = MutableLiveData()

    fun getUserData() {

        /*myScope.launch {

            for ( i in 1..200000)
                Log.d("MyTag", "$i")
        }*/

        //viewModelScope is bound to ViewModel's lifecycle. It is created to automatically handle cancellation when the
        //ViewModel's on clear is called. viewModelScope is a CoroutineScope tied to a ViewModel.

        viewModelScope.launch {

            var result: List<User>? = null

            withContext(Dispatchers.IO){
                result = UserRepository().getUsers()
            }

            usersList.value = result!!

        }
    }

    //Some of the coroutines we launch in a view model have the potential to run even after the view model is cleared from memory
    //. It might run until our app is terminated. If that is not what we intend to do, this will result in memory
    //leaks. To avoid this we need to cancel the coroutine in onCleared() method which will be called when ViewModel is cleared
    //from memory. In order to do that, we need to pass job instance to the context of the coroutine scope so
    // that we can terminate the coroutine scope using the job instance.

    /*override fun onCleared() {
        super.onCleared()
        myJob.cancel() //cancels all the coroutines in this context

        // When number of view model classes is large, it is not a good practice to handle cancelling of
        //coroutines manually. In order to avoid this boiler plate code, we can use viewModelScope - kotlin extension property.
    }*/
}