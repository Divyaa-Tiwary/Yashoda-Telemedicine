package hospital.yashoda.ps_1_project

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hospital.yashoda.ps_1_project.doctor.DoctorLogin
import hospital.yashoda.ps_1_project.login.ActivityLogin
import kotlinx.android.synthetic.main.activity_welcome_page.*

class WelcomePage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome_page)
        init()
    }

    private fun init() {
        setListeners()
    }

    private fun setListeners() {
        image_patient.setOnClickListener {
            val intent = Intent(this, ActivityLogin::class.java)
            startActivity(intent)
        }
        emergency_login_text.setOnClickListener {
            val intent = Intent(this, EmergencyService::class.java)
            startActivity(intent)
        }
        imageDoctor.setOnClickListener{
            val intent = Intent(this,
                DoctorLogin::class.java)
            startActivity(intent)
        }
    }

    companion object {
        fun newInstance(context: Context) =
            Intent(context, WelcomePage::class.java)
    }


}


