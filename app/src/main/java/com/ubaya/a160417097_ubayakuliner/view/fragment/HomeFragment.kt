package com.ubaya.a160417097_ubayakuliner.view.fragment

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.ubaya.a160417097_ubayakuliner.GlobalData
import com.ubaya.a160417097_ubayakuliner.R
import com.ubaya.a160417097_ubayakuliner.model.Restaurant
import com.ubaya.a160417097_ubayakuliner.view.adapter.RestaurantListAdapter
import com.ubaya.a160417097_ubayakuliner.viewmodel.RestaurantListViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_restaurant_list.*
import java.util.*
import kotlin.collections.ArrayList

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {

    private lateinit var restoViewModel: RestaurantListViewModel
    private val restaurantListAdapter = RestaurantListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        GlobalData.currentFragment = "Home"

        restoViewModel = ViewModelProvider(this).get(RestaurantListViewModel::class.java)
        restoViewModel.refresh()

        recViewRecomended.layoutManager = LinearLayoutManager(context)
        recViewRecomended.adapter = restaurantListAdapter

        observeRestoViewModel()

        buttonPindah.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToRestaurantListFragment()
            Navigation.findNavController(it).navigate(action)
        }

        setHasOptionsMenu(true)
    }

    private fun observeRestoViewModel() {
        restoViewModel.restaurantsLD.observe(viewLifecycleOwner) {
            val arr: List<Restaurant> = it.slice(0..2)
            restaurantListAdapter.updateRestaurantList(ArrayList(arr))
        }
        restoViewModel.restaurantsLoadErrorLD.observe(viewLifecycleOwner) {
            textErrorHomeRecomended.visibility = if(it) View.VISIBLE else View.GONE
//            if(it) {
//                textError.visibility = View.VISIBLE
//            } else {
//                textError.visibility = View.GONE
//            }
        }
        restoViewModel.loadingLD.observe(viewLifecycleOwner) {
            if (it) {
                recViewRecomended.visibility = View.GONE
                progressHomeRecomended.visibility = View.VISIBLE
            } else {
                recViewRecomended.visibility = View.VISIBLE
                progressHomeRecomended.visibility = View.GONE
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_search, menu)
        val item = menu?.findItem(R.id.itemSearch)
        val searchView = item?.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                TODO("Not yet implemented")
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val searchText = newText!!.toLowerCase(Locale.getDefault())

                return false
            }
        })

        return super.onCreateOptionsMenu(menu, inflater)
    }
}