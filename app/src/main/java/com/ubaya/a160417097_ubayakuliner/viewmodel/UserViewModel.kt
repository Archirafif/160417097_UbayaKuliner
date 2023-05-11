package com.ubaya.a160417097_ubayakuliner.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.android.volley.Request
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ubaya.a160417097_ubayakuliner.model.User

class UserViewModel(application: Application): AndroidViewModel(application) {
    val userLiveData = MutableLiveData<User>()

    val TAG = "volleyTAG"
    private var queue: RequestQueue ?= null

    fun fetch(){

        queue = Volley.newRequestQueue(getApplication())
        var url = "https://archirafif.github.io/ubaya-kuliner-json/user-profile.json"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                val sType = object : TypeToken<User>() { }.type
                val result = Gson().fromJson<User>(response, sType)
                userLiveData.value = result
                Log.d("showvolley", response.toString())
            },
            {
                Log.d("errorvolley", it.toString())
            }
        ).apply {
            tag = "TAG"
        }

        queue?.add(stringRequest)
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}