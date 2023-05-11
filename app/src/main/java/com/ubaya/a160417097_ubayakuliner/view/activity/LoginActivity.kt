package com.ubaya.a160417097_ubayakuliner.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ubaya.a160417097_ubayakuliner.R
import com.ubaya.a160417097_ubayakuliner.model.User
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        editUsername.setText("s160417097")
        editPassword.setText("12345")

        buttonLogin.setOnClickListener {
            val username = inputUsername.editText?.text.toString()
            val password = inputPassword.editText?.text.toString()

            queue = Volley.newRequestQueue(this)
            var url = "https://archirafif.github.io/ubaya-kuliner-json/user-profile.json"
            val stringRequest = StringRequest(
                Request.Method.GET, url,
                { response ->
                    val sType = object : TypeToken<User>() { }.type
                    val result = Gson().fromJson<User>(response, sType)
//                    for(item in result){
                        if(result.username == username){
                            if(result.password == password){
                                val intent = Intent(this, MainActivity::class.java)
                                startActivity(intent)
                                finish()
                            } else {
                                Toast.makeText(this, "Your password is incorrect", Toast.LENGTH_SHORT).show()
                            }
                        } else {
                            Toast.makeText(this, "Your username is incorrect", Toast.LENGTH_SHORT).show()
                        }
//                    }
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
    }
}