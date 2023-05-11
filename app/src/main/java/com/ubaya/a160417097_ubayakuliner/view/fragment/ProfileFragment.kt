package com.ubaya.a160417097_ubayakuliner.view.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.ubaya.a160417097_ubayakuliner.R
import com.ubaya.a160417097_ubayakuliner.view.activity.LoginActivity
import com.ubaya.a160417097_ubayakuliner.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_restaurant_menu_detail.*

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileFragment : Fragment() {
    private lateinit var viewModel:UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        viewModel.fetch()

        observeViewModel()

        buttonProfileDetail.setOnClickListener {
            val action = ProfileFragmentDirections.actionItemProfileToProfileDetailFragment()
            Navigation.findNavController(it).navigate(action)
        }

        buttonLogout.setOnClickListener {
            val intent = Intent(activity, LoginActivity::class.java)
            startActivity(intent)
        }

    }

    private fun observeViewModel(){
        viewModel.userLiveData.observe(viewLifecycleOwner) {
            textUserName.text = it.name
        }
    }
}