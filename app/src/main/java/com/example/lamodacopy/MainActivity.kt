package com.example.lamodacopy

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.size
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.example.lamodacopy.databinding.ActivityMainBinding
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNavigationView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        when (item.itemId) {
            R.id.notFragment -> {
                val nextFragment = NotFragment()

                navController.navigate(R.id.nots)

                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    object BottomNavigationUtils {
        fun changeBottomNavigationItem(bottomNavigationView: BottomNavigationView, context: Context) {
            Handler(Looper.getMainLooper()).post {
                val menu = bottomNavigationView.menu
                val homeItem = menu.findItem(R.id.enterFragment)
                homeItem.title = "Профиль"
                homeItem.icon = ContextCompat.getDrawable(context, R.drawable.profile)

                bottomNavigationView.selectedItemId = homeItem.itemId
            }
        }
        fun replaceMenuItem(bottomNavigationView: BottomNavigationView, context: Context) {
            val menu = bottomNavigationView.menu
            menu.removeItem(R.id.enterFragment)

            val newItem = menu.add(Menu.NONE, R.id.profileFragment, Menu.NONE, "Профиль")
            newItem.icon = ContextCompat.getDrawable(context, R.drawable.profile)

        }
    }

}