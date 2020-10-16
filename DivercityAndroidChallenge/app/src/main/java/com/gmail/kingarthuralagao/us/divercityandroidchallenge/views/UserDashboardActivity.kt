package com.gmail.kingarthuralagao.us.divercityandroidchallenge.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.gmail.kingarthuralagao.us.divercityandroidchallenge.R
import com.gmail.kingarthuralagao.us.divercityandroidchallenge.databinding.ActivityUserDashboardBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlin.properties.Delegates

class UserDashboardActivity : AppCompatActivity(), HomeFragment.ISortDataListener, SortDialogFragment.ISortByListener,
    EditNameDialogFragment.IFinishEditListener, ProfileFragment.IEditNameListener, MenuFragment.ILogOut {

    private lateinit var userDashboardBinding : ActivityUserDashboardBinding
    private var userId by Delegates.notNull<Int>()

    private val homeFragment : HomeFragment by lazy {
        HomeFragment.newInstance()
    }

    private val profileFragment : ProfileFragment by lazy {
        ProfileFragment.newInstance(userId)
    }

    private val menuFragment : MenuFragment by lazy {
        MenuFragment.newInstance()
    }

    private lateinit var activeFragment : Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userDashboardBinding = ActivityUserDashboardBinding.inflate(layoutInflater)
        setContentView(userDashboardBinding.root)
        setSupportActionBar(userDashboardBinding.userDashBoardToolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        userId = intent.getIntExtra(resources.getString(R.string.id), 0)
        userDashboardBinding.userDashBoardToolbar.title = resources.getString(R.string.home)
        userDashboardBinding.bottomNavigation.setOnNavigationItemSelectedListener(onNavigationSelectedListener)
        userDashboardBinding.bottomNavigation.selectedItemId = R.id.home
    }

    private fun switchFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()

        when (fragment) {
            homeFragment -> {
                if (supportFragmentManager.findFragmentByTag(resources.getString(R.string.home)) == null) {
                    activeFragment = fragment
                    transaction.add(userDashboardBinding.fragmentContainer.id, homeFragment, resources.getString(R.string.home))
                }
            }

            profileFragment -> {
                if (supportFragmentManager.findFragmentByTag(resources.getString(R.string.profile)) == null) {
                    transaction.add(userDashboardBinding.fragmentContainer.id, profileFragment, resources.getString(R.string.profile))
                }
            }

            else -> {
                if (supportFragmentManager.findFragmentByTag(resources.getString(R.string.menu)) == null) {
                    transaction.add(userDashboardBinding.fragmentContainer.id, menuFragment, resources.getString(R.string.menu))
                }
            }
        }

        transaction.hide(activeFragment)
        transaction.show(fragment)
        activeFragment = fragment
        //transaction.replace(userDashboardBinding.fragmentContainer.id, fragment)
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

    override fun onEditName(firstName: String, lastName: String) {
        val editNameDialogFragment = EditNameDialogFragment.newInstance(firstName, lastName)
        editNameDialogFragment.show(supportFragmentManager, "")
    }
    override fun onFinishEdit(firstName : String, lastName : String) {
        if (firstName.isNotEmpty() && lastName.isNotEmpty()) {
            profileFragment.updateUserFullName(firstName, lastName)
        }
    }

    override fun onLogOutClicked() {
        finish()
    }
}
