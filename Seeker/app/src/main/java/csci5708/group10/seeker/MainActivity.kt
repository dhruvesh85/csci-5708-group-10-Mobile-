package csci5708.group10.seeker

import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    val navView: BottomNavigationView = findViewById(R.id.nav_view)
    val navController = findNavController(R.id.nav_host_fragment)

    navView.setupWithNavController(navController)

        /*navView.setOnNavigationItemSelectedListener{ item: MenuItem ->
            return@setOnNavigationItemSelectedListener when (item.itemId) {
                R.id.navigation_profile -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, ProfileFragment()).commit()
                    true
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
        }*/

    }

}
