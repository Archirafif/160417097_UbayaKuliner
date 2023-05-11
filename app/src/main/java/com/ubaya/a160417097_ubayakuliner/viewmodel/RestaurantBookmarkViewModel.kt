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

class RestaurantBookmarkViewModel(application: Application): AndroidViewModel(application) {

    val restaurantsBookmarkLD = MutableLiveData<ArrayList<Restaurant>>()
    val restaurantsBookmarkLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()

    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun refresh() {
        loadingLD.value = true
        restaurantsBookmarkLoadErrorLD.value = false

        queue = Volley.newRequestQueue(getApplication())
        var url = "https://archirafif.github.io/ubaya-kuliner-json/restaurant-bookmark.json"
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                val sType = object : TypeToken<ArrayList<Restaurant>>() { }.type
                val result = Gson().fromJson<ArrayList<Restaurant>>(response, sType)
                restaurantsBookmarkLD.value = result
                loadingLD.value = false
                Log.d("showvolley", response.toString())
            },
            {
                loadingLD.value = false
                restaurantsBookmarkLoadErrorLD.value = true
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