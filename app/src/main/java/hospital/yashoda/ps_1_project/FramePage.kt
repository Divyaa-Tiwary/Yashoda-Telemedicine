package hospital.yashoda.ps_1_project

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_frame_page.*
import kotlinx.android.synthetic.main.content_main.*

class FramePage : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_frame_page)
        init()
    }

    private fun init() {
        setUpNavBar()
        setListeners()
    }

    private fun setUpNavBar() {
        setSupportActionBar(toolbar)
        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, 0, 0
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)
    }

    private fun setListeners(){
        box1.setOnClickListener {
            val intent = Intent(this,PhysicalConsultation::class.java)
            startActivity(intent)
        }
        box2.setOnClickListener {
            val intent = Intent(this,VideoConsultation::class.java)
            startActivity(intent)
        }
        box3.setOnClickListener {
            val intent = Intent(this,MedicalReportsPage::class.java)
            startActivity(intent)
        }
        box4.setOnClickListener {
            val intent = Intent(this,BillsPage::class.java)
            startActivity(intent)
        }
        box5.setOnClickListener {
            val intent = Intent(this,PrescriptionPage::class.java)
            startActivity(intent)
        }
        box6.setOnClickListener {
            val intent = Intent(this,EmergencyService::class.java)
            startActivity(intent)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_profile -> {
                Toast.makeText(this, "Profile clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_appointments -> {
                Toast.makeText(this, "Messages clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_update -> {
                Toast.makeText(this, "Update clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_signout -> {
                Toast.makeText(this, "Sign out clicked", Toast.LENGTH_SHORT).show()
            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}