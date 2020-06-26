package hospital.yashoda.ps_1_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hospital.yashoda.ps_1_project.utils.PreferenceUtils
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        init()
    }

    private fun init() {
        setupUI()
    }

    private fun setupUI() {
        val pm = PreferenceUtils
        pname.text = pm.name
        page.text = pm.age
        pgen.text = pm.gender
        pno.text = pm.phone
        pmail.text = pm.email
        pstate.text = pm.state
    }
}