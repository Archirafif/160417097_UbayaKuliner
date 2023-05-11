package com.ubaya.a160417097_ubayakuliner.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.ubaya.a160417097_ubayakuliner.GlobalData
import com.ubaya.a160417097_ubayakuliner.R
import com.ubaya.a160417097_ubayakuliner.util.loadImage
import com.ubaya.a160417097_ubayakuliner.viewmodel.RestaurantMenusListViewModel
import kotlinx.android.synthetic.main.fragment_restaurant_menu.*
import kotlinx.android.synthetic.main.fragment_restaurant_menu_detail.*

/**
 * A simple [Fragment] subclass.
 * Use the [RestaurantMenuDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RestaurantMenuDetailFragment : BottomSheetDialogFragment() {

    private lateinit var menuViewModel: RestaurantMenusListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_restaurant_menu_detail, container, false)
    }
    var menuID = ""
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.let{
            menuID = RestaurantMenuDetailFragmentArgs.fromBundle(requireArguments()).restaurantMenuID
        }

        menuViewModel = ViewModelProvider(this).get(RestaurantMenusListViewModel::class.java)
        menuViewModel.refresh()

        observeMenuViewModel()
    }

    private fun observeMenuViewModel(){
        menuViewModel.restaurantMenusLiveData.observe(viewLifecycleOwner) {
            for((index, item) in it.withIndex()){
                if(item.restaurantID == GlobalData.restoID){
                    for(menu in item.items){
                        if(menu.menuID == menuID) {
                            textMenuDetailName.text = menu.name
                            textMenuDetailPrice.text = "Rp ${menu.price}"
                            textMenuDescription.text = menu.description
                            imageMenuPhotoDetail.loadImage(menu.photo, progressBarMenuDetail)
                        }
                    }
                }
            }
        }
    }
}