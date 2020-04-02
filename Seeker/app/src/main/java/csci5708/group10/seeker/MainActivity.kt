package csci5708.group10.seeker

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    val navView: BottomNavigationView = findViewById(R.id.nav_view)

    val navController = findNavController(R.id.nav_host_fragment)
    // Passing each menu ID as a set of Ids because each
    // menu should be considered as top level destinations.
//        val appBarConfiguration = AppBarConfiguration(
//            setOf(
//                R.id.navigation_home, R.id.navigation_my_recipe, R.id.navigation_profile
//            )
//        )
//        setupActionBarWithNavController(navController, appBarConfiguration)
    navView.setupWithNavController(navController)

        navView.setOnNavigationItemSelectedListener{ item: MenuItem ->
            return@setOnNavigationItemSelectedListener when (item.itemId) {
                R.id.navigation_profile -> {
                   val sf=getSharedPreferences("loginpref",0)
                    val str: String? = sf.getString("login", "")
                    if(str.equals("1"))
                    {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, ProfileFragment()).commit()
                        true
                    }
//                    val ed=sf.edit()
//                    ed.putString("login","0")
//                    ed.commit()
//                    ed.apply()
                     else{
                        val intent = Intent(this, login_act::class.java)
                        startActivity(intent)
                        true
                    }

                }
                R.id.navigation_library -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, LibraryFragment()).commit()
                    true
                }
                R.id.navigation_settings -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, SettingsFragment()).commit()
                    true
                }
                R.id.navigation_add_book -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, AddBookFragment()).commit()
                    true
                }
                R.id.navigation_home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, HomeFragment()).commit()
                    true
                }
                else -> false
            }
        }

    }

}
