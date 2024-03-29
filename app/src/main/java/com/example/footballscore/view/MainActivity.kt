package com.example.footballscore.view

import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.footballscore.R
import com.example.footballscore.viewModel.ShredPreferenc
import com.example.footballscore.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        shredPreferenc = ShredPreferenc()

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
                R.id.navigation_user -> fragment = ProfilFragment()
                R.id.navigation_football -> fragment = FootballFragment()
                R.id.navigation_sıralama -> fragment= SiralamaFragment()
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




