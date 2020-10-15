package com.gmail.kingarthuralagao.us.divercityandroidchallenge.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.gmail.kingarthuralagao.us.divercityandroidchallenge.R
import com.gmail.kingarthuralagao.us.divercityandroidchallenge.databinding.ActivityUserDashboardBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class UserDashboardActivity : AppCompatActivity(), HomeFragment.SortListener, SortDialogFragment.SortByListener{

    lateinit var userDashboardBinding : ActivityUserDashboardBinding

    private val homeFragment : HomeFragment by lazy {
        HomeFragment.newInstance()
    }

    private val profileFragment : ProfileFragment by lazy {
        ProfileFragment.newInstance()
    }

    private val menuFragment : MenuFragment by lazy {
        MenuFragment.newInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userDashboardBinding = ActivityUserDashboardBinding.inflate(layoutInflater)
        setContentView(userDashboardBinding.root)
        setSupportActionBar(userDashboardBinding.userDashBoardToolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        userDashboardBinding.userDashBoardToolbar.title = resources.getString(R.string.home)
        userDashboardBinding.bottomNavigation.setOnNavigationItemSelectedListener(onNavigationSelectedListener)
        userDashboardBinding.bottomNavigation.selectedItemId = R.id.home
    }

    private fun switchFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(userDashboardBinding.fragmentContainer.id, fragment)
        transaction.commit()
    }

    private val onNavigationSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener {
        when(it.itemId) {
            R.id.home -> {
                userDashboardBinding.userDashBoardToolbar.title = resources.getString(R.string.home)
                switchFragment(homeFragment)
            }
            R.id.profile -> {
                userDashboardBinding.userDashBoardToolbar.title = resources.getString(R.string.profile)
                switchFragment(profileFragment)
            }
            else -> {
                userDashboardBinding.userDashBoardToolbar.title = resources.getString(R.string.menu)
                switchFragment(menuFragment)
            }
        }
        return@OnNavigationItemSelectedListener true
    }

    override fun onSortBtnClick() {
        val sortDialogFragment = SortDialogFragment()
        sortDialogFragment.show(supportFragmentManager, "")
    }

    override fun onSortOptionPicked(sortBy: String, sortOption: String) {
        homeFragment.sortItems(sortBy, sortOption)
    }
}
