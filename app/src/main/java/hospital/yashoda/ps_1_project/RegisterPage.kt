package hospital.yashoda.ps_1_project

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import hospital.yashoda.ps_1_project.constants.USERS
import hospital.yashoda.ps_1_project.pojos.Users
import hospital.yashoda.ps_1_project.utils.PreferenceUtils
import kotlinx.android.synthetic.main.activity_doctor_registration_page.*

class RegisterPage : AppCompatActivity() {

    private lateinit var name: String
    private lateinit var email: String
    private val pm = PreferenceUtils

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_page)
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
            Log.d("TAG!!!!","clocked")
            val firestore = FirebaseFirestore.getInstance()
            firestore.collection(USERS).document(email).set(
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
                val intent = Intent(this, FramePage::class.java)
                startActivity(intent)
            }.addOnFailureListener {
                Log.d("TAG!!!!",it.toString())
            }
        }
    }

    companion object {

        private const val NAME = "name"
        private const val EMAIL = "email"

        fun newInstance(context: Context, name: String, email: String) =
            Intent(context, RegisterPage::class.java).apply {
                putExtra(NAME, name)
                putExtra(EMAIL, email)
            }
    }

}