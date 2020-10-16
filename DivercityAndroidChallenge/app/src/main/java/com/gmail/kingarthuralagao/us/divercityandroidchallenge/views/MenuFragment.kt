package com.gmail.kingarthuralagao.us.divercityandroidchallenge.views

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gmail.kingarthuralagao.us.divercityandroidchallenge.databinding.FragmentMenuBinding
import java.lang.RuntimeException

class MenuFragment : Fragment() {

    interface ILogOut {
        fun onLogOutClicked()
    }

    companion object {
        fun newInstance() : MenuFragment = MenuFragment()
    }

    private lateinit var menuBinding: FragmentMenuBinding
    private var logOutListener : ILogOut? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        menuBinding = FragmentMenuBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return menuBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        menuBinding.logout.setOnClickListener {
            logOutListener?.onLogOutClicked()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ILogOut) {
            logOutListener = context
        } else {
            throw RuntimeException("Must implement ILogOut")
        }
    }

    override fun onDetach() {
        super.onDetach()
        logOutListener = null
    }
}