package com.android.distilled.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.distilled.R
import com.android.distilled.databinding.ActivityMainBinding
import com.android.distilled.di.DaggerAppComponent
import com.android.distilled.viewmodels.HomeViewModel
import com.android.distilled.viewmodels.ViewModelFactory
import javax.inject.Inject

class ShowsActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var mViewModel: HomeViewModel
    lateinit var binding: ActivityMainBinding
    private lateinit var showsListAdapter: ShowsListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        DaggerAppComponent.create().inject(this)
        initViewModel()
        initObservers()
        initRecyclerView()
        setContentView(binding.root)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.sortBy -> {
                showBottomOptionsDialog()
                return true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    private fun initObservers() {
        mViewModel.handleError.observe(this, {
            binding.rvShows.visibility = View.GONE
            binding.tvError.visibility = View.VISIBLE
            binding.tvError.text = when (it) {
                HomeViewModel.Errors.ERROR_NO_INTERNET -> "Internet is not available, Please enable from device settings and try again."
                HomeViewModel.Errors.ERROR_EMPTY_LIST -> "Data is not available, Please try again after sometime."
                else -> "Something went wrong on sever, Please try after sometime."
            }
        })

        mViewModel.handleData.observe(this, {
            binding.rvShows.visibility = View.VISIBLE
            binding.tvError.visibility = View.GONE
            showsListAdapter.refreshList(it)
        })
    }

    private fun initViewModel() {
        mViewModel = ViewModelProvider(this, viewModelFactory).get(HomeViewModel::class.java)
    }

    private fun initRecyclerView() {
        showsListAdapter = ShowsListAdapter(mutableListOf())
        binding.rvShows.apply {
            adapter = showsListAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun showBottomOptionsDialog() {
        BottomSheetDialog {
            mViewModel.getTvShowsFromDb(it)
        }.show(supportFragmentManager, "ShowsActivity")
    }
}