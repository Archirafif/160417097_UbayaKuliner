package com.ubaya.a160417097_ubayakuliner.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.a160417097_ubayakuliner.GlobalData
import com.ubaya.a160417097_ubayakuliner.R
import com.ubaya.a160417097_ubayakuliner.model.Restaurant
import com.ubaya.a160417097_ubayakuliner.util.loadImage
import com.ubaya.a160417097_ubayakuliner.view.fragment.BookmarkFragmentDirections
import com.ubaya.a160417097_ubayakuliner.view.fragment.HomeFragmentDirections
import com.ubaya.a160417097_ubayakuliner.view.fragment.RestaurantListFragmentDirections
import kotlinx.android.synthetic.main.card_restaurant_list_item.view.*
import kotlinx.android.synthetic.main.fragment_restaurant_detail.view.*

class RestaurantListAdapter(val restaurantList: ArrayList<Restaurant>) : RecyclerView
.Adapter<RestaurantListAdapter.RestaurantViewHolder>() {
    class RestaurantViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.card_restaurant_list_item, parent, false)
        return RestaurantViewHolder(view)
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        val restaurant = restaurantList[position]
        with(holder.view){
            textRestaurantName.text = restaurant.name
            imageRestaurantListPhoto.loadImage(restaurant.photo, progressBarRestaurantListPhoto)

            buttonDetail.setOnClickListener {
                var action: NavDirections
                if(GlobalData.currentFragment == "Home"){
                    action = HomeFragmentDirections.actionItemHomeToRestaurantDetailFragment(restaurant.id.toString())
                } else if(GlobalData.currentFragment == "Restaurant List") {
                    action = RestaurantListFragmentDirections.actionRestaurantListFragmentToRestaurantDetailFragment(restaurant.id.toString())
                } else {
                    action = BookmarkFragmentDirections.actionItemBookmarkToRestaurantDetailFragment(restaurant.id.toString())
                }
                Navigation.findNavController(it).navigate(action)
            }
        }
    }

    override fun getItemCount() = restaurantList.size

    fun updateRestaurantList(newRestaurantList: ArrayList<Restaurant>) {
        restaurantList.clear()
        restaurantList.addAll(newRestaurantList)
        notifyDataSetChanged()
    }
}