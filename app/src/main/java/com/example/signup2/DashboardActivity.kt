package com.example.signup2

import android.content.Intent

import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.signup2.data.LoginRepository
import com.example.signup2.databinding.ActivityDashboardBinding
import com.example.signup2.ui.LoginPhone.loginPhoneActivity
import com.example.signup2.ui.Settings.SettingsActivity

class DashboardActivity : AppCompatActivity() {
    private lateinit var loginRepository: LoginRepository
    private lateinit var binding: ActivityDashboardBinding
    private lateinit var appBarConfiguration: AppBarConfiguration


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)




        loginRepository = LoginRepository(this, null)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        val loginRepository = LoginRepository(this, null)

        setSupportActionBar(toolbar)
        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView

        val headerView = navView.getHeaderView(0)
        val tvEmail = headerView.findViewById<TextView>(R.id.tvEmail)
        val tvFullname = headerView.findViewById<TextView>(R.id.tvFullname)
        tvEmail.text = loginRepository.session!!.user!!.email
        tvFullname.text = loginRepository.session!!.user!!.fullname
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home,
                R.id.nav_gallery,
                R.id.nav_slideshow,
                R.id.nav_role,
                R.id.nav_location,
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

















    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.dashboard, menu)

        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle presses on the action bar menu items
        when (item.itemId) {
            R.id.action_logout -> {
                logoutClick()
                return true
            }
            R.id.action_settings -> {
                settingsClick()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
    private fun logoutClick() {
        loginRepository.logout()
        val intent = Intent(this, loginPhoneActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
        finish()
    }
    private fun settingsClick() {
        val intent = Intent(this, SettingsActivity::class.java)
        startActivity(intent)
    }

}