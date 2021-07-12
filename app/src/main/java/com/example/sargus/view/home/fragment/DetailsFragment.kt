package com.example.sargus.view.home.fragment

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sargus.R
import com.example.sargus.data.local.model.UserRepos
import com.example.sargus.databinding.FragmentDetailsBinding
import com.example.sargus.di.Injectable
import com.example.sargus.utility.Logger
import com.example.sargus.utility.Resource
import com.example.sargus.utility.showSnackBar
import com.example.sargus.view.home.adapter.RvAdapterPosts
import com.example.sargus.view.home.viewmodel.DetailsViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val ARG_PARAM3 = "param3"
private const val ARG_PARAM4 = "param4"

class DetailsFragment : Fragment(), Injectable {
    private var param1: String? = null
    private var param2: String? = null
    private var param3: String? = null
    private var param4: String? = null

    private lateinit var binding: FragmentDetailsBinding
    private lateinit var rvAdapter: RvAdapterPosts


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    val detailsViewModel: DetailsViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
            param3 = it.getString(ARG_PARAM3)
            param4 = it.getString(ARG_PARAM4)
        }

        rvAdapter= RvAdapterPosts()
        setHasOptionsMenu(true)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false)
        binding.viewModel=detailsViewModel

        binding.name?.text = param2
        binding.phone?.text = param3
        binding.email?.text = param4

        val toolbar = binding.toolbar
        (activity as AppCompatActivity?)!!.setSupportActionBar(toolbar)
        (activity as AppCompatActivity?)!!.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity?)!!.supportActionBar!!.setDisplayShowHomeEnabled(true);

        return binding.root
    }


        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Logger.setLog(message = "$detailsViewModel  is Received")
        setUpRecyclerView()

        param1?.let {
            detailsViewModel.getPosts(it).observe(viewLifecycleOwner, Observer {
                when (it.status) {
                    Resource.LOADING -> {
                        Logger.setLog("loading", "start loading")
                        detailsViewModel.isLoading = true
                    }
                    Resource.SUCCESS -> {
                        // here we need to setup recyclerview
                        val list = it.data as List<UserRepos>
                        Logger.setLog("here", list.toString())
                        rvAdapter.addDataList(list)
                        detailsViewModel.isLoading = false
                    }
                    Resource.ERROR -> {
                        detailsViewModel.isLoading = false
                        it.message?.let { it1 -> homeContainer.showSnackBar(it1) }
                    }
                }
            })
        }
    }

    fun setUpRecyclerView(){
        binding.recyclerViewPostsResults.apply {
            layoutManager= LinearLayoutManager(activity)
            adapter=rvAdapter
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String,param3: String, param4: String) =
            DetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                    putString(ARG_PARAM3, param3)
                    putString(ARG_PARAM4, param4)
                }
            }
        }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                (activity as AppCompatActivity?)!!.onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}