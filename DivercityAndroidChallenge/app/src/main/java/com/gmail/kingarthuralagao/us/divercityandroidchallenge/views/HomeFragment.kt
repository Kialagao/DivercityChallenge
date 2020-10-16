package com.gmail.kingarthuralagao.us.divercityandroidchallenge.views

import android.content.Context
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
import java.lang.RuntimeException

class HomeFragment : Fragment() {

    interface ISortDataListener {
        fun onSortBtnClick()
    }

    companion object {
        fun newInstance() : HomeFragment = HomeFragment()
    }

    private val TAG = javaClass.simpleName
    private lateinit var homeFragmentBinding : FragmentHomeBinding
    private lateinit var viewManager : LinearLayoutManager
    private lateinit var viewAdapter : UsersRecyclerViewAdapter
    private lateinit var itemDecorator : VerticalSpaceItemDecoration
    private lateinit var homeFragmentViewModel : HomeFragmentViewModel
    private var sortListener : ISortDataListener? = null

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
        homeFragmentBinding.sortBtn.setOnClickListener {
            sortListener?.onSortBtnClick()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ISortDataListener) {
            sortListener = context
        } else {
            throw RuntimeException("Must implement SortListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        sortListener = null
    }

    fun sortItems(sortBy : String, sortOption : String) {
        Log.d(TAG, sortBy + "" + sortOption)
        homeFragmentViewModel.sortData(sortBy, sortOption)
        viewAdapter.setData(homeFragmentViewModel.userListLiveData.value!!)
    }

    private val usersListObserver = Observer<MutableList<User>> { list ->
        if (!list.isNullOrEmpty()) {
            Log.d(TAG, "InObserver")
            //list.sortBy { it.getDateOfBirth() }
            viewAdapter.setData(list)
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