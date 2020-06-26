package hospital.yashoda.ps_1_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import hospital.yashoda.ps_1_project.utils.PreferenceUtils

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        val mGoogleSignInClient = GoogleSignIn.getLastSignedInAccount(this)

        if (mGoogleSignInClient?.account == null) {
            Handler().postDelayed({
                startActivity(WelcomePage.newInstance(this))
                overridePendingTransition(R.anim.fadein, R.anim.fadeout)
            }, 2000)
        } else {
            if (PreferenceUtils.doctor) {
                Handler().postDelayed({
                    startActivity(Intent(this,DoctorFrame::class.java))
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout)
                }, 2000)
            } else {
                Handler().postDelayed({
                    startActivity(Intent(this,FramePage::class.java))
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout)
                }, 2000)
            }
        }
    }
}