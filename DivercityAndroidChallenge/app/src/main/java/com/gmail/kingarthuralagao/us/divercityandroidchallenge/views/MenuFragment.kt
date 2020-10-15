package com.gmail.kingarthuralagao.us.divercityandroidchallenge.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gmail.kingarthuralagao.us.divercityandroidchallenge.databinding.FragmentHomeBinding
import com.gmail.kingarthuralagao.us.divercityandroidchallenge.databinding.FragmentMenuBinding
import com.gmail.kingarthuralagao.us.divercityandroidchallenge.databinding.FragmentProfileBinding

class MenuFragment : Fragment() {

    companion object {
        fun newInstance() : MenuFragment = MenuFragment()
    }

    lateinit var profileBinding : FragmentMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        profileBinding = FragmentMenuBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return profileBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}