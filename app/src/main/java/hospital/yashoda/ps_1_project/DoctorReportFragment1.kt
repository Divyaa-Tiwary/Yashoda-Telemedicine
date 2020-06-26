package hospital.yashoda.ps_1_project

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import kotlinx.android.synthetic.main.fragment_doctor_report1.*


class DoctorReportFragment1 : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_doctor_report1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        setListeners()
    }

    private fun setListeners() {
        Button.setOnClickListener {
            if (EditText.text.toString().isEmpty()) {
                Toast.makeText(requireContext(), "Enter patient id",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            changeFragment(DoctorReportFragment2.newInstance(EditText.text.toString().trim()))
        }
    }

    private fun changeFragment(fragment: Fragment, tag: String = "") {
        val fragmentTransaction: FragmentTransaction? = activity?.supportFragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.FrameContainer, fragment)?.addToBackStack(null)
        fragmentTransaction?.commit()
    }

    companion object {

        fun newInstance() = DoctorReportFragment1()
    }
}