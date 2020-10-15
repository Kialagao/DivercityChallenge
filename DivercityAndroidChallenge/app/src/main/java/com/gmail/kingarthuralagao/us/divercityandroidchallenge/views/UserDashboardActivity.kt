package com.gmail.kingarthuralagao.us.divercityandroidchallenge.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.gmail.kingarthuralagao.us.divercityandroidchallenge.R
import com.gmail.kingarthuralagao.us.divercityandroidchallenge.databinding.ActivityUserDashboardBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class UserDashboardActivity : AppCompatActivity() {

    lateinit var userDashboardBinding : ActivityUserDashboardBinding

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
                switchFragment(HomeFragment.newInstance())
            }
            R.id.profile -> {
                userDashboardBinding.userDashBoardToolbar.title = resources.getString(R.string.profile)
                switchFragment(ProfileFragment.newInstance())
            }
            else -> {
                userDashboardBinding.userDashBoardToolbar.title = resources.getString(R.string.menu)
                switchFragment(MenuFragment.newInstance())
            }
        }
        return@OnNavigationItemSelectedListener true
    }
}
