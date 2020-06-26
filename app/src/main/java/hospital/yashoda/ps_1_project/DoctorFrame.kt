package hospital.yashoda.ps_1_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import kotlinx.android.synthetic.main.content_main.*

class DoctorFrame : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor_frame)
        init()
    }

    private fun init() {
        setUpFragment()
    }

    private fun setUpFragment() {
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.FrameContainer, FrameFragment.newInstance())
        fragmentTransaction.commit()
    }

}