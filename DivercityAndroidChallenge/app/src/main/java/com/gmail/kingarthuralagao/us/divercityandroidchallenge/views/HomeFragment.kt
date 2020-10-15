package com.gmail.kingarthuralagao.us.divercityandroidchallenge.views

import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gmail.kingarthuralagao.us.divercityandroidchallenge.adapters.UsersRecyclerViewAdapter
import com.gmail.kingarthuralagao.us.divercityandroidchallenge.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() : HomeFragment = HomeFragment()
    }

    private val TAG = javaClass.simpleName
    private lateinit var homeFragmentBinding : FragmentHomeBinding
    private lateinit var viewManager : LinearLayoutManager
    private lateinit var viewAdapter : UsersRecyclerViewAdapter
    private lateinit var itemDecorator : VerticalSpaceItemDecoration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeFragmentBinding = FragmentHomeBinding.inflate(layoutInflater)

        viewManager = LinearLayoutManager(requireContext())
        val data = mutableListOf("Hello", "Hi")
        viewAdapter = UsersRecyclerViewAdapter(data)
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