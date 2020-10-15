package com.gmail.kingarthuralagao.us.divercityandroidchallenge.views

import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gmail.kingarthuralagao.us.divercityandroidchallenge.adapters.UsersRecyclerViewAdapter
import com.gmail.kingarthuralagao.us.divercityandroidchallenge.databinding.FragmentHomeBinding
import com.gmail.kingarthuralagao.us.divercityandroidchallenge.models.User
import com.gmail.kingarthuralagao.us.divercityandroidchallenge.viewmodels.HomeFragmentViewModel

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() : HomeFragment = HomeFragment()
    }

    private val TAG = javaClass.simpleName
    private lateinit var homeFragmentBinding : FragmentHomeBinding
    private lateinit var viewManager : LinearLayoutManager
    private lateinit var viewAdapter : UsersRecyclerViewAdapter
    private lateinit var itemDecorator : VerticalSpaceItemDecoration
    private lateinit var homeFragmentViewModel : HomeFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeFragmentBinding = FragmentHomeBinding.inflate(layoutInflater)
        homeFragmentViewModel = ViewModelProvider(this).get(HomeFragmentViewModel::class.java)
        homeFragmentViewModel.userListLiveData.observe(this, usersListObserver)

        val dummyList = mutableListOf<User>()
        viewAdapter = UsersRecyclerViewAdapter(dummyList)
        viewManager = LinearLayoutManager(requireContext())
        itemDecorator = VerticalSpaceItemDecoration(96)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeFragmentBinding.usersRv.apply {
            addItemDecoration(itemDecorator)
            layoutManager = viewManager
            adapter = viewAdapter
        }
        Log.d(TAG, "onCreateView")
        return homeFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeFragmentViewModel.getUsers()
    }

    private val usersListObserver = Observer<MutableList<User>> {
        if (!it.isNullOrEmpty()) {
            Log.d(TAG, "InObserver")
            viewAdapter.setData(it)
        } else {
            Log.d(TAG, "Empty or Null ")
        }
    }
}

class VerticalSpaceItemDecoration(private val verticalSpaceHeight: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.bottom = verticalSpaceHeight;
    }
}