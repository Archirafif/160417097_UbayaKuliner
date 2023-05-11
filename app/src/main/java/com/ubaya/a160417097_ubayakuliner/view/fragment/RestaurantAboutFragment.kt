package com.ubaya.a160417097_ubayakuliner.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.ubaya.a160417097_ubayakuliner.GlobalData
import com.ubaya.a160417097_ubayakuliner.R
import com.ubaya.a160417097_ubayakuliner.util.loadImage
import com.ubaya.a160417097_ubayakuliner.viewmodel.RestaurantDetailViewModel
import kotlinx.android.synthetic.main.fragment_restaurant_about.*

/**
 * A simple [Fragment] subclass.
 * Use the [RestaurantAboutFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RestaurantAboutFragment : Fragment() {
    private lateinit var viewModel: RestaurantDetailViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_restaurant_about, container, false)
    }
    var restoID = ""
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        restoID = GlobalData.restoID

        viewModel = ViewModelProvider(this).get(RestaurantDetailViewModel::class.java)
        viewModel.fetch(restoID)

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.restaurantDetailLiveData.observe(viewLifecycleOwner) {
            val restaurantDetail = viewModel.restaurantDetailLiveData.value
            restaurantDetail?.let{
                textRestoName.text = it.name
                textRestoAddress.text = "Address: ${it.address}"
                textRestoRating.text = it.rating
                imageViewRestoDetailPhoto.loadImage(it.photo, progressBarRestoDetailPhoto)

                textMondayOH.text = it.opening_hours.monday
                textTuesdayOH.text = it.opening_hours.tuesday
                textWednesdayOH.text = it.opening_hours.wednesday
                textThursdayOH.text = it.opening_hours.thursday
                textFridayOH.text = it.opening_hours.friday
                textSaturdayOH.text = it.opening_hours.saturday
                textSundayOH.text = it.opening_hours.sunday
            }
        }
    }
}