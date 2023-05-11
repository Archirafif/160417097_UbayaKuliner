package com.ubaya.a160417097_ubayakuliner.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ubaya.a160417097_ubayakuliner.GlobalData
import com.ubaya.a160417097_ubayakuliner.R
import com.ubaya.a160417097_ubayakuliner.view.adapter.RestaurantReviewAdapter
import com.ubaya.a160417097_ubayakuliner.viewmodel.RestaurantDetailViewModel
import kotlinx.android.synthetic.main.fragment_restaurant_about.*
import kotlinx.android.synthetic.main.fragment_restaurant_list.*
import kotlinx.android.synthetic.main.fragment_restaurant_reviews.*

/**
 * A simple [Fragment] subclass.
 * Use the [RestaurantReviewsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RestaurantReviewsFragment : Fragment() {

    private lateinit var viewModel: RestaurantDetailViewModel
    private val restaurantReviewAdapter = RestaurantReviewAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_restaurant_reviews, container, false)
    }
    var restoID = ""
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        restoID = GlobalData.restoID
        viewModel = ViewModelProvider(this).get(RestaurantDetailViewModel::class.java)
        viewModel.fetch(restoID)

        recViewRestaurantReviews.layoutManager = LinearLayoutManager(context)
        recViewRestaurantReviews.adapter = restaurantReviewAdapter

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.restaurantDetailLiveData.observe(viewLifecycleOwner) {
            val restaurantDetail = viewModel.restaurantDetailLiveData.value
            restaurantDetail?.let{
                restaurantReviewAdapter.updateRestaurantReviewList(it.reviews)
            }
        }
    }

}