package hospital.yashoda.ps_1_project.doctor

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import hospital.yashoda.ps_1_project.DoctorFrame
import hospital.yashoda.ps_1_project.utils.PreferenceUtils
import hospital.yashoda.ps_1_project.R
import hospital.yashoda.ps_1_project.constants.DOCTORS
import hospital.yashoda.ps_1_project.pojos.Users
import kotlinx.android.synthetic.main.activity_doctor_registration_page.*

class DoctorRegistrationPage : AppCompatActivity() {


    private lateinit var name: String
    private lateinit var email: String
    private val pm = PreferenceUtils

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor_registration_page)
        intent?.let {
            name = it.getStringExtra(NAME)
            email = it.getStringExtra(EMAIL)
        }
        init()
    }

    private fun init() {
        setListeners()
        enterName.hint = name
        enterEmail.hint = email
        setPreferences()
    }

    private fun setPreferences() {
        pm.name = name
        pm.email = email
        pm.phone = enterNumber.text.toString()
        pm.age = enterAge.text.toString()
        pm.gender = enterSex.text.toString()
        pm.state = enterState.text.toString()
        pm.country = "India"
    }

    private fun setListeners() {
        enterButton.setOnClickListener {
            val firestore = FirebaseFirestore.getInstance()
            firestore.collection(DOCTORS).document(email).set(
                Users(
                    name,
                    enterAge.text.toString(),
                    enterSex.text.toString(),
                    enterNumber.text.toString(),
                    email,
                    enterState.text.toString(),
                    "",
                    "","",""
                )
            ).addOnSuccessListener {
                val intent = Intent(this, DoctorFrame::class.java)
                startActivity(intent)
                pm.doctor = true
                Toast.makeText(this,"Logged in",Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Log.d("TAG!!!!",it.toString())
            }
        }
    }

    companion object {

        private const val NAME = "name"
        private const val EMAIL = "email"

        fun newInstance(context: Context, name: String, email: String) =
            Intent(context, DoctorRegistrationPage::class.java).apply {
                putExtra(NAME, name)
                putExtra(EMAIL, email)
            }
    }
}