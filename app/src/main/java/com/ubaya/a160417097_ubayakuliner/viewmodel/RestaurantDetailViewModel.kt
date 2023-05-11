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
import com.ubaya.a160417097_ubayakuliner.model.Restaurant

class RestaurantDetailViewModel(application: Application) : AndroidViewModel(application){
    val restaurantDetailLiveData = MutableLiveData<Restaurant>()

    val TAG = "volleyTag"
    private var queue: RequestQueue ?= null

    fun fetch(id: String?) {
        queue = Volley.newRequestQueue(getApplication())
        var url = "https://archirafif.github.io/ubaya-kuliner-json/restaurant-near-ubaya.json"
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                val sType = object : TypeToken<ArrayList<Restaurant>>() { }.type
                val result = Gson().fromJson<ArrayList<Restaurant>>(response, sType)
                for(item in result){
                    if(item.id == id){
                        restaurantDetailLiveData.value = item
                    }
                }
                //restaurantDetailLiveData.value = result
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