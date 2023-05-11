package com.ubaya.a160417097_ubayakuliner.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.a160417097_ubayakuliner.R
import com.ubaya.a160417097_ubayakuliner.model.Item
import com.ubaya.a160417097_ubayakuliner.util.loadImage
import com.ubaya.a160417097_ubayakuliner.view.fragment.*
import kotlinx.android.synthetic.main.card_restaurant_list_item.view.*
import kotlinx.android.synthetic.main.card_restaurant_menu.view.*

class RestaurantMenuAdapter(val restaurantMenuList: ArrayList<Item>) : RecyclerView.Adapter<RestaurantMenuAdapter.RestaurantMenuViewHolder>() {
    class RestaurantMenuViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantMenuViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.card_restaurant_menu, parent, false)
        return RestaurantMenuViewHolder(view)
    }

    override fun onBindViewHolder(holder: RestaurantMenuViewHolder, position: Int) {
        val restaurantMenu = restaurantMenuList[position]
        Log.d("menu",restaurantMenu.toString())
        with(holder.view) {
            textMenuName.text = restaurantMenu.name
            textMenuPrice.text = "Rp. ${restaurantMenu.price.toString()}"
            imageMenuPhoto.loadImage(restaurantMenu.photo, progressBarRestaurantMenuPhoto)

            buttonMenuDetail.setOnClickListener {
                val action = RestaurantDetailFragmentDirections.actionRestaurantDetailFragmentToRestaurantMenuDetailFragment(restaurantMenu.menuID.toString())
                Navigation.findNavController(it).navigate(action)
            }
        }
    }

    override fun getItemCount() = restaurantMenuList.size

    fun updateRestaurantMenuList(newRestauranMenuList: ArrayList<Item>) {
        restaurantMenuList.clear()
        restaurantMenuList.addAll(newRestauranMenuList)
        notifyDataSetChanged()
    }
}