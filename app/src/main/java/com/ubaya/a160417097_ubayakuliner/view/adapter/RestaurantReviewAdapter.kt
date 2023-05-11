package com.ubaya.a160417097_ubayakuliner.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.a160417097_ubayakuliner.R
import com.ubaya.a160417097_ubayakuliner.model.Review
import kotlinx.android.synthetic.main.card_restaurant_review.view.*

class RestaurantReviewAdapter(val restaurantReviewList: ArrayList<Review>) : RecyclerView.Adapter<RestaurantReviewAdapter.RestaurantReviewViewHolder>() {
    class RestaurantReviewViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantReviewViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.card_restaurant_review, parent, false)
        return RestaurantReviewViewHolder(view)
    }

    override fun onBindViewHolder(holder: RestaurantReviewViewHolder, position: Int) {
        val restaurantReview = restaurantReviewList[position]
        with(holder.view){
            textReviewName.text = restaurantReview.name
            textReviewDate.text = restaurantReview.date
            textReviewComment.text = restaurantReview.comments
            textReviewRating.text = restaurantReview.rating
        }
    }

    override fun getItemCount() = restaurantReviewList.size

    fun updateRestaurantReviewList(newRestaurantList: ArrayList<Review>){
        restaurantReviewList.clear()
        restaurantReviewList.addAll(newRestaurantList)
        notifyDataSetChanged()
    }
}