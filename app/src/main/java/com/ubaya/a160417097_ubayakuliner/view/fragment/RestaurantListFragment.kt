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
import com.ubaya.a160417097_ubayakuliner.view.adapter.RestaurantListAdapter
import com.ubaya.a160417097_ubayakuliner.viewmodel.RestaurantListViewModel
import kotlinx.android.synthetic.main.fragment_restaurant_list.*

/**
 * A simple [Fragment] subclass.
 * Use the [RestaurantListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RestaurantListFragment : Fragment() {
    private lateinit var viewModel: RestaurantListViewModel
    private val restaurantListAdapter = RestaurantListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_restaurant_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        GlobalData.currentFragment = "Restaurant List"

        viewModel = ViewModelProvider(this).get(RestaurantListViewModel::class.java)
        viewModel.refresh()

        recViewRL.layoutManager = LinearLayoutManager(context)
        recViewRL.adapter = restaurantListAdapter

        observeViewModel()

        refreshLayoutRL.setOnRefreshListener {
            recViewRL.visibility = View.GONE
            textErrorRL.visibility = View.GONE
            progressLoadRL.visibility = View.VISIBLE
            viewModel.refresh()
            refreshLayoutRL.isRefreshing = false
        }
    }

    private fun observeViewModel() {
        viewModel.restaurantsLD.observe(viewLifecycleOwner) {
            restaurantListAdapter.updateRestaurantList(it)
        }
        viewModel.restaurantsLoadErrorLD.observe(viewLifecycleOwner) {
            textErrorRL.visibility = if(it) View.VISIBLE else View.GONE
        }
        viewModel.loadingLD.observe(viewLifecycleOwner) {
            if (it) {
                recViewRL.visibility = View.GONE
                progressLoadRL.visibility = View.VISIBLE
            } else {
                recViewRL.visibility = View.VISIBLE
                progressLoadRL.visibility = View.GONE
            }
        }
    }
}