package hospital.yashoda.ps_1_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.google.firebase.firestore.FirebaseFirestore
import hospital.yashoda.ps_1_project.utils.PreferenceUtils
import kotlinx.android.synthetic.main.activity_bills_page.*

class BillsPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bills_page)
        init()
    }

    private fun init() {
        fetchData()
    }

    private fun fetchData() {
        val pm = PreferenceUtils.email
        val firestore = FirebaseFirestore.getInstance()
        firestore.collection("Users").document(pm).get().addOnSuccessListener {
            if ((it["bill"].toString() == "").not())
                Glide.with(this).load(it["bill"]).into(image)
        }
    }
}