package hospital.yashoda.ps_1_project

import android.app.DatePickerDialog
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.DialogInterface
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.util.Linkify
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.auth.User
import hospital.yashoda.ps_1_project.constants.DOCTORS
import hospital.yashoda.ps_1_project.utils.PreferenceUtils
import kotlinx.android.synthetic.main.activity_physical_consultation.*
import java.util.*

class PhysicalConsultation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_physical_consultation)
        setListeners()
    }

    private fun setListeners() {
        button1.setOnClickListener {
            val dialog = AlertDialog.Builder(this)
            dialog.apply {
                setTitle("Confirm booking?")
                setMessage("Do you want to book an appointment with Dr. Lalitha?")
                setPositiveButton("Book now") { dialog, which ->
                    showDateDialog(dialog as AlertDialog?)
                }
                setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which ->

                })
            }
            dialog.show()
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun showDateDialog(view: AlertDialog?) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                view?.setMessage("Appointment for " + "$dayOfMonth-${monthOfYear + 1}-$year")
                // view?.setText("$dayOfMonth-${monthOfYear + 1}-$year")
            }, year, month, day
        )
        datePickerDialog.setOnDateSetListener { view, year, month, dayOfMonth ->
//            final SpannableString s = new SpannableString(msg); // msg should have url to enable clicking
//            Linkify.addLinks(s, Linkify.ALL);
            val s = SpannableString("https://meet.google.com/wgy-dmgd-ief")
            Linkify.addLinks(s, Linkify.ALL)
            val dialog = android.app.AlertDialog.Builder(this)
            dialog.setTitle("Video consultation booked. Here is your link")
            dialog.setMessage(s)
            dialog.setPositiveButton("Cool",DialogInterface.OnClickListener { dialog, which ->
                val firestore = FirebaseFirestore.getInstance()
                val pm = PreferenceUtils
                firestore.collection(DOCTORS).document("Lalitha").collection("Patients").document().set(pm.getUser()).addOnSuccessListener {
                    dialog.dismiss()
                    Toast.makeText(this@PhysicalConsultation, "Appointment booked", Toast.LENGTH_SHORT).show()
                }
            })
            dialog.setNegativeButton("Cancel", DialogInterface.OnClickListener {dialog, which ->
                dialog.dismiss()
            })
            dialog.show()
        }
        datePickerDialog.show()
    }
}