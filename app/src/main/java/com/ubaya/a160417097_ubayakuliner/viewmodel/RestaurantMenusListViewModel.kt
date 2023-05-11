package com.ubaya.a160417097_ubayakuliner.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ubaya.a160417097_ubayakuliner.model.Menu

class RestaurantMenusListViewModel(application: Application) : AndroidViewModel(application) {
    val restaurantMenusLiveData = MutableLiveData<ArrayList<Menu>>()
    val restaurantMenusLoadErrorLiveData = MutableLiveData<Boolean>()
    val loadingLiveData = MutableLiveData<Boolean>()

    val TAG = "volleyTag"
    private var queue:RequestQueue ?= null

    fun refresh() {
        loadingLiveData.value = true
        restaurantMenusLoadErrorLiveData.value = false

        queue = Volley.newRequestQueue(getApplication())
        var url = "https://archirafif.github.io/ubaya-kuliner-json/menu-restaurant.json"
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                val sType = object : TypeToken<ArrayList<Menu>>() { }.type
                val result = Gson().fromJson<ArrayList<Menu>>(response, sType)
                restaurantMenusLiveData.value = result
                loadingLiveData.value = false
                Log.d("showvolley", response.toString())
            },
            {
                loadingLiveData.value = false
                restaurantMenusLoadErrorLiveData.value = true
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