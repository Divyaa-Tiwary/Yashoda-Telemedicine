package hospital.yashoda.ps_1_project.doctor

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import hospital.yashoda.ps_1_project.DoctorFrame
import hospital.yashoda.ps_1_project.FramePage
import hospital.yashoda.ps_1_project.R
import hospital.yashoda.ps_1_project.RegisterPage
import hospital.yashoda.ps_1_project.pojos.Users
import hospital.yashoda.ps_1_project.utils.PreferenceUtils
import kotlinx.android.synthetic.main.activity_doctor_login.*

class DoctorLogin : AppCompatActivity() {

    private val RC_SIGN_IN = 123

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor_login)

        val gso =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()

        val mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        sign_in_button.setSize(SignInButton.SIZE_WIDE)
        sign_in_button.setOnClickListener {
            val signInIntent = mGoogleSignInClient.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task =
                GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account =
                completedTask.getResult(ApiException::class.java)


            val firestore = FirebaseFirestore.getInstance()
            firestore.collection("Users").document(account?.email.toString()).get()
                .addOnSuccessListener {
                    if (it.exists()) {
                        val pm = PreferenceUtils
                        pm.setUser(
                            Users(
                                it["name"].toString(),
                                it["age"].toString(),
                                it["gender"].toString(),
                                it["phoneNumber"].toString(),
                                it["email"].toString(),
                                it["state"].toString(),
                                it["country"].toString(),
                                "",
                                "",
                                ""
                            )
                        )
                        startActivity(
                            Intent(this, DoctorFrame::class.java)
                        )
                    } else {
                        startActivity(
                            DoctorRegistrationPage.newInstance(
                                this,
                                account?.displayName.toString(),
                                account?.email.toString()
                            )
                        )
                    }
                }

        } catch (e: ApiException) {
            Log.d("TAG", e.toString() + "error")
        }
    }

}