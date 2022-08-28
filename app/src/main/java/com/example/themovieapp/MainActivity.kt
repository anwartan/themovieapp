package com.example.themovieapp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.example.themovieapp.databinding.ActivityMainBinding
import com.example.themovieapp.favorite.FavoriteFragment
import com.example.themovieapp.home.HomeFragment
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setSupportActionBar(binding.appBarMain.topAppBar)
        setContentView(binding.root)

        val toogle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            binding.appBarMain.topAppBar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        binding.drawerLayout.addDrawerListener(toogle)
        toogle.syncState()

        binding.navView.setNavigationItemSelectedListener(this)

        if(savedInstanceState==null){
            supportFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment, HomeFragment())
                .commit()
            binding.navView.setCheckedItem(R.id.nav_home)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.top_app_bar,menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.search_menu ->{

//                val uri = Uri.parse("themovieapp://search")
                val intent = Intent(this,Class.forName("com.example.themovieapp.search.SearchActivity"))
                startActivity(intent)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var fragment:Fragment? = null
        var title = getString(R.string.app_name)
        when(item.itemId){
            R.id.nav_home->{
                fragment = HomeFragment()
                title = getString(R.string.app_name)
            }
            R.id.nav_favorite->{
                fragment = FavoriteFragment()
                title = getString(R.string.menu_favorite)
            }

        }
        if(fragment != null){
            supportFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment,fragment)
                .commit()
        }
        supportActionBar?.title = title

        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }


}