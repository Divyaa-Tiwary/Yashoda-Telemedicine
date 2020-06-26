package hospital.yashoda.ps_1_project

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import hospital.yashoda.ps_1_project.pojos.Adapter
import hospital.yashoda.ps_1_project.pojos.Model
import kotlinx.android.synthetic.main.activity_emergency_service.*
import kotlinx.android.synthetic.main.contact_list.*
import kotlinx.android.synthetic.main.contact_list.view.*
import kotlinx.android.synthetic.main.contact_list.view.share


class EmergencyService : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_emergency_service)


        val arrayList = ArrayList<Model>()
        arrayList.add(Model("SOMAJIGUDA",
            "Rajbhavan Road, Somajiguda, Matha Nagar,Hyderabad, Telangana",
                "+91 40 – 4567 4567, +91 40 – 2331 2783", "somajiguda@yashodamail.com"))

        arrayList.add(Model("MALAKPET",
            "Nalgonda X Roads, Malakpet, Hyderabad – 500036" ,
            "+91 40 – 4567 4567, +91 40 – 2455 6257", "malakpet@yashodamail.com"))

        arrayList.add(Model("SECUNDERABAD",
            "Alexander Road, Secunderabad, Hyderabad – 500003" ,
            "+91 40 – 4567 4567, +91 40 – 2770 3999", "secunderabad@yashodamail.com"))

        arrayList.add(
            Model("HI-TECH CITY",
        "Kothaguda, Hyderabad, Telangana 500084",
        "+91 40 – 4567 4567","info@yashodamail.com"))


        val adapter = Adapter(arrayList ,this)

        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.adapter =  adapter


    }
}
