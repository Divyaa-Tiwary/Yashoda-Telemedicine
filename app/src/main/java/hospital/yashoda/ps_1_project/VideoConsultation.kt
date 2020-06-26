package hospital.yashoda.ps_1_project

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.Dialog
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.DialogInterface
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.util.Linkify
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.google.firebase.firestore.FirebaseFirestore
import hospital.yashoda.ps_1_project.utils.PreferenceUtils
import kotlinx.android.synthetic.main.activity_video_consultation.*
import java.util.*

class VideoConsultation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_consultation)
        setListeners()
    }

    private fun setListeners() {
        button1.setOnClickListener {
            val dialog = AlertDialog.Builder(this)
            dialog.setTitle("Book video consultation?")
            dialog.setMessage("Are you sure you want to book?")
            dialog.setPositiveButton("Book now",DialogInterface.OnClickListener { dialog, which ->
                showDateDialog(dialog as AlertDialog?)
            })
            dialog.setNegativeButton("Cancel", DialogInterface.OnClickListener {dialog, which ->
                dialog.dismiss()
            })
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
            val dialog = AlertDialog.Builder(this)
            dialog.setTitle("Video consultation booked. Here is your link")
            dialog.setMessage(s)
            dialog.setPositiveButton("Cool",DialogInterface.OnClickListener { dialog, which ->
                val firestore = FirebaseFirestore.getInstance()
                val pm = PreferenceUtils
                firestore.collection("Videos").document("Lalitha").collection("Patients").document().set(pm.getUser()).addOnSuccessListener {
                    val myClip = ClipData.newPlainText("text", s);
                    val myClipboard: ClipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                    myClipboard.setPrimaryClip(myClip)
                    Toast.makeText(this, "Link copied to clipboard", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
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