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
import com.ubaya.a160417097_ubayakuliner.viewmodel.RestaurantBookmarkViewModel
import kotlinx.android.synthetic.main.fragment_bookmark.*
import kotlinx.android.synthetic.main.fragment_restaurant_list.*

/**
 * A simple [Fragment] subclass.
 * Use the [BookmarkFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BookmarkFragment : Fragment() {

    private lateinit var viewModel: RestaurantBookmarkViewModel
    private val restaurantListAdapter = RestaurantListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bookmark, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        GlobalData.currentFragment = "Bookmark"

        viewModel = ViewModelProvider(this).get(RestaurantBookmarkViewModel::class.java)
        viewModel.refresh()

        recViewBookmark.layoutManager = LinearLayoutManager(context)
        recViewBookmark.adapter = restaurantListAdapter

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.restaurantsBookmarkLD.observe(viewLifecycleOwner) {
            restaurantListAdapter.updateRestaurantList(it)
        }
        viewModel.restaurantsBookmarkLoadErrorLD.observe(viewLifecycleOwner) {
            textErrorBookmark.visibility = if(it) View.VISIBLE else View.GONE
        }
        viewModel.loadingLD.observe(viewLifecycleOwner) {
            if (it) {
                recViewBookmark.visibility = View.GONE
                progressLoadBookmark.visibility = View.VISIBLE
            } else {
                recViewBookmark.visibility = View.VISIBLE
                progressLoadBookmark.visibility = View.GONE
            }
        }
    }
}