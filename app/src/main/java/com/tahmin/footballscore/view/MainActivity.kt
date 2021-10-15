package com.tahmin.footballscore.view

import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.tahmin.footballscore.R
import com.tahmin.footballscore.viewModel.ShredPreferenc
import com.tahmin.footballscore.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.orhanobut.hawk.Hawk

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        shredPreferenc = ShredPreferenc()

        Hawk.init(context).build()

        val navView = findViewById<BottomNavigationView>(R.id.nav_view)
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        navView.itemIconTintList = null
        loadFragment(HomeFragment())
    }
    var txtHosgeldiniz: TextView? = null
    var shredPreferenc: ShredPreferenc? = null
    var context: Context = this
    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            var fragment: Fragment? = null
            when (item.itemId) {
                R.id.navigation_home -> fragment = HomeFragment()
                R.id.navigation_user -> fragment = ProfileFragment()
                R.id.navigation_football -> fragment = FootballFragment()
                R.id.navigation_sıralama -> fragment= ArrangementFragment()
            }
            loadFragment(fragment)
        }

   fun loadFragment(fragment: Fragment?): Boolean {
        if (fragment != null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit()
            return true
        }
        return false
    }

    fun onNavigationItemSelected(item: MenuItem): Boolean {
        return false
    }
}




