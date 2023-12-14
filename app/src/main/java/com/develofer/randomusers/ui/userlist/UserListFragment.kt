package com.develofer.randomusers.ui.userlist

import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.WindowCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.develofer.randomusers.R
import com.develofer.randomusers.databinding.FragmentUserListBinding
import com.develofer.randomusers.domain.data.UserDomain
import com.develofer.randomusers.utils.CustomTypefaceSpan
import com.google.android.material.search.SearchView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserListFragment: Fragment() {

    private val viewModel: UserListViewModel by viewModels()
    private var binding: FragmentUserListBinding? = null
    private lateinit var userListAdapter: UserListAdapter
    private var users: MutableList<UserDomain>? = null
    private var currentPage = 1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserListBinding.inflate(inflater, container, false)
        
        setUpView()
        setUpObservers()
        fetchData()

        return binding?.root
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
        toolbar?.title = getString(R.string.contacts).uppercase()
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
                    super.onScrolled(recyclerView, dx, dy)
                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                    val visibleItemCount = layoutManager.childCount
                    val totalItemCount = layoutManager.itemCount
                    val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount && firstVisibleItemPosition >= 0) {
                        currentPage++
                        fetchData()
                    }
                }

            })
        }
    }

    private fun setUpObservers() {
        setUpCategoryObserver()
    }

    private fun setUpCategoryObserver() {
        viewModel.getUsersLiveData().observe(viewLifecycleOwner) {
            removeDuplicatesAndSubmitList(it)
        }
    }

    private fun fetchData() {
        viewModel.getUsers(currentPage)
    }

    private fun removeDuplicatesAndSubmitList(userDomains: List<UserDomain>) {
        val uniqueUsers = userDomains.distinctBy { it.email }.toMutableList()
        userListAdapter.submitList(uniqueUsers)
    }

    override fun onResume() {
        users?.let { userListAdapter.submitList(it) }
        super.onResume()
    }

}