package hospital.yashoda.ps_1_project

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_doctor_list.*
import java.util.*


class DoctorList : Fragment() {
    private var mRecyclerView: RecyclerView? = null
    var DocList = ArrayList<DoctorItem>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_doctor_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        initData()
    }

    private fun init() {
        doclist.setHasFixedSize(true)
        doclist.layoutManager = LinearLayoutManager(requireContext())
        doclist.adapter = DocAdapter(initData())
    }

    private fun initData(): ArrayList<DoctorItem> {
        DocList.add(DoctorItem(R.drawable.ic_launcher_background, "Dr. ABC", "MBBS"))
        DocList.add(DoctorItem(R.drawable.ic_launcher_background, "Dr. PQR", "B.Pharm"))
        DocList.add(DoctorItem(R.drawable.ic_launcher_background, "Dr. XYZ", "Specialist"))
        return DocList
    }
}