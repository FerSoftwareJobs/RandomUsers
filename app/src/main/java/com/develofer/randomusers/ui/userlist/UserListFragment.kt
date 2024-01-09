package com.develofer.randomusers.ui.userlist

import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.WindowCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.develofer.randomusers.R
import com.develofer.randomusers.databinding.FragmentUserListBinding
import com.develofer.randomusers.utils.CustomTypefaceSpan
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserListFragment: Fragment() {

    private val viewModel: UserListViewModel by viewModels()
    private var binding: FragmentUserListBinding? = null
    private lateinit var userListAdapter: UserListAdapter
    private var searchText: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserListBinding.inflate(inflater, container, false)
        setUpView()
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpObservers()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun setUpView() {
        setUpToolbar()
        setUpStatusBar()
        setUpRecyclerView()
    }

    private fun setUpToolbar() {
        val toolbar = binding?.tbUserListFragmentToolbar
        toolbar?.setupWithNavController(navController = findNavController())
        toolbar?.title = getString(R.string.fragment_user_list__contacts).uppercase()
        val customTypeface = Typeface.createFromAsset(context?.assets, "oswald_medium.ttf")
        val spannableString = SpannableString(toolbar?.title)
        spannableString.setSpan(
            CustomTypefaceSpan(
                "",
                customTypeface
            ),
            0,
            spannableString.length,
            Spannable.SPAN_INCLUSIVE_INCLUSIVE
        )
        toolbar?.title = spannableString
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        setHasOptionsMenu(true)
    }

    private fun setUpStatusBar() {
        val windowInsetsController =
            activity?.window?.let {
                WindowCompat.getInsetsController(it, it.decorView)
            }
        windowInsetsController?.apply {
            isAppearanceLightStatusBars = true
        }
    }

    private fun setUpRecyclerView() {
        context?.let { context ->
            userListAdapter = UserListAdapter(
                context,
                onItemClick = { user ->
                    findNavController().navigate(
                        UserListFragmentDirections.actionListFragmentToDetailFragment(user)
                    )
                }
            )
        }
        binding?.tvUserListFragmentUserList?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = userListAdapter
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    if (viewModel.isShowingRawList()) {
                        super.onScrolled(recyclerView, dx, dy)
                        val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                        val visibleItemCount = layoutManager.childCount
                        val totalItemCount = layoutManager.itemCount
                        val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
                        if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount && firstVisibleItemPosition >= 0) {
                            loadMoreUsers()
                        }
                    }
                }
            })
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)

        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.isNullOrBlank()) {
                    viewModel.showRawList()
                } else {
                    searchText = newText
                }
                return true
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when (item.itemId) {
            R.id.action_filter_by_name -> {
                filterListByName()
                true
            }
            R.id.action_filter_by_email -> {
                filterListByEmail()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    private fun filterListByName() {
        searchText?.let {
            viewModel.filterByName(searchText.orEmpty())
        }
    }

    private fun filterListByEmail() {
        searchText?.let {
            viewModel.filterByEmail(searchText.orEmpty())
        }
    }

    private fun loadMoreUsers() {
        viewModel.increasePage()
        viewModel.getUsers()
    }

    private fun setUpObservers() {
        setUpCategoryObserver()
    }

    private fun setUpCategoryObserver() {
        viewModel.getUsersLiveData().observe(viewLifecycleOwner) {
            userListAdapter.submitList(it)
            removeProgress()
        }
    }

    private fun removeProgress() {
        binding?.apply {
            tvUserListFragmentUserList.visibility = View.VISIBLE
            pbUserListFragmentProgress.visibility = View.GONE
        }
    }

}