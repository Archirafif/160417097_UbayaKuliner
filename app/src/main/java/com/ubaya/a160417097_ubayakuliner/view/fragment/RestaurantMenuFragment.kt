package com.ubaya.a160417097_ubayakuliner.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ubaya.a160417097_ubayakuliner.GlobalData
import com.ubaya.a160417097_ubayakuliner.R
import com.ubaya.a160417097_ubayakuliner.view.adapter.RestaurantMenuAdapter
import com.ubaya.a160417097_ubayakuliner.viewmodel.RestaurantMenusListViewModel
import kotlinx.android.synthetic.main.fragment_restaurant_list.*
import kotlinx.android.synthetic.main.fragment_restaurant_menu.*

/**
 * A simple [Fragment] subclass.
 * Use the [RestaurantMenuFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RestaurantMenuFragment : Fragment() {
    private lateinit var menuViewModel: RestaurantMenusListViewModel
    private val menuListAdapter = RestaurantMenuAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_restaurant_menu, container, false)
    }

    var restoID = ""
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        restoID = GlobalData.restoID

        menuViewModel = ViewModelProvider(this).get(RestaurantMenusListViewModel::class.java)
        menuViewModel.refresh()

        recViewRestaurantMenu.layoutManager = LinearLayoutManager(context)
        recViewRestaurantMenu.adapter = menuListAdapter

        observeMenuViewModel()
    }

    private fun observeMenuViewModel(){
        menuViewModel.restaurantMenusLiveData.observe(viewLifecycleOwner) {
            for((index, item) in it.withIndex()){
                if(item.restaurantID == restoID) {
                    Log.d("itemMenu", item.toString())
                    menuListAdapter.updateRestaurantMenuList(item.items)
                }
            }
        }
        menuViewModel.restaurantMenusLoadErrorLiveData.observe(viewLifecycleOwner) {
            txtErrorRML.visibility = if(it) View.VISIBLE else View.GONE
        }
        menuViewModel.loadingLiveData.observe(viewLifecycleOwner) {
            if (it) { // sedang loading
                recViewRestaurantMenu.visibility = View.GONE
                progressRML.visibility = View.VISIBLE
            } else {
                recViewRestaurantMenu.visibility = View.VISIBLE
                progressRML.visibility = View.GONE
            }
        }
    }
}