package com.example.sargus.view.home.fragment

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sargus.R
import com.example.sargus.data.local.model.Users
import com.example.sargus.databinding.FragmentHomeBinding
import com.example.sargus.di.Injectable
import com.example.sargus.utility.Logger
import com.example.sargus.utility.Resource
import com.example.sargus.utility.showSnackBar
import com.example.sargus.view.home.adapter.RvAdapterUsers
import com.example.sargus.view.home.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.fragment_home.*

import javax.inject.Inject

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class HomeFragment : Fragment(), Injectable {
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding: FragmentHomeBinding
    private lateinit var rvAdapter: RvAdapterUsers


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    val homeViewModel: HomeViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)

        }
        rvAdapter= RvAdapterUsers()
        setHasOptionsMenu(true)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.viewModel=homeViewModel

        val toolbar = binding.toolbar
        (activity as AppCompatActivity?)!!.setSupportActionBar(toolbar)
        (activity as AppCompatActivity?)!!.supportActionBar!!.setDisplayHomeAsUpEnabled(false)
        (activity as AppCompatActivity?)!!.supportActionBar!!.setDisplayShowHomeEnabled(false);
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Logger.setLog(message = "$homeViewModel  is Received")
        setUpRecyclerView()

        homeViewModel.getUsers().observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.LOADING -> {
                    Logger.setLog("loading", "start loading")
                    homeViewModel.isLoading = true
                }
                Resource.SUCCESS -> {
                    // here we need to setup recyclerview
                    val list=it.data as List<Users>
                    Logger.setLog("here",list.toString())
                    rvAdapter.addDataList(list)
                    homeViewModel.isLoading = false


                }
                Resource.ERROR -> {
                    homeViewModel.isLoading = false
                    it.message?.let { it1 -> homeContainer.showSnackBar(it1) }
                }
            }
        })

    }


    fun setUpRecyclerView(){
        binding.homeRecyclerView.apply {
            layoutManager=LinearLayoutManager(activity)
            adapter=rvAdapter
        }
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.home_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id: Int = item.itemId
        if (id == R.id.menusetting) {
            NavHostFragment.findNavController(fragment)
                .navigate(R.id.action_homeFragment_to_settingFragment)
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}