package hospital.yashoda.ps_1_project

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import kotlinx.android.synthetic.main.content_main.*


class FrameFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_frame, container, false)
    }


    private fun setupUI() {
//        box3.visibility = View.GONE
//        box3text.visibility = View.GONE
//        box3image.visibility = View.GONE
//        box4.visibility = View.GONE
//        box4text.visibility = View.GONE
//        box4image.visibility = View.GONE
//        box5.visibility = View.GONE
//        box5text.visibility = View.GONE
//        box5image.visibility = View.GONE
//        box6.visibility = View.GONE
//        box6text.visibility = View.GONE
//        box6image.visibility = View.GONE
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        setupUI()
        setListeners()
    }


    private fun setListeners() {
        box4.setOnClickListener {
            changeFragment(DoctorReportFragment1.newInstance(), tag = "Report page")
        }
        box5.setOnClickListener {
            changeFragment(DoctorPrescriptionFragment1.newInstance(), tag = "Prescription Page")
        }
        box3.setOnClickListener {
            changeFragment(DoctorReportFragment1.newInstance())
        }
        box4.setOnClickListener {
            changeFragment(DoctorBills1.newInstance())
        }
    }


    private fun changeFragment(fragment: Fragment, tag: String = "") {
        val fragmentTransaction: FragmentTransaction? = activity?.supportFragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.FrameContainer, fragment)?.addToBackStack(null)
        fragmentTransaction?.commit()
    }

    companion object {

        fun newInstance() = FrameFragment()
    }
}
