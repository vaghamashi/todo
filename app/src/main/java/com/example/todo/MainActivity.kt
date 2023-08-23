package com.example.todo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.example.todo.Fragment.AccountFragment
import com.example.todo.Fragment.TasksFragment
import com.example.todo.Fragment.calendarFragment
import com.example.todo.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationBarView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {


lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)




        initView()

    }
    fun initView() {
        binding.bootappdar.setOnMenuItemClickListener(object :
            NavigationView.OnNavigationItemSelectedListener,
            NavigationBarView.OnItemSelectedListener, Toolbar.OnMenuItemClickListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {

                when (item.itemId) {

                    R.id.tasks -> {
                        loadfragment(TasksFragment())

                    }

                    R.id.calendar -> {
                        loadfragment(calendarFragment())
                    }

                    R.id.Account -> {
                        loadfragment(AccountFragment())
                    }

                }
                return true
            }

            override fun onMenuItemClick(item: MenuItem?): Boolean {
                when (item?.itemId) {

                    R.id.tasks -> {
                        loadfragment(TasksFragment())


                    }

                    R.id.calendar -> {
                        loadfragment(calendarFragment())
                    }

                    R.id.Account -> {
                        loadfragment(AccountFragment())
                    }

                }
                return true
            }


        })


    }
    private fun loadfragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fram, fragment).commit()

    }

}