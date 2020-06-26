package hospital.yashoda.ps_1_project

import android.app.DatePickerDialog.OnDateSetListener
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.activity_book_appointment.*
import java.text.DateFormat
import java.util.*

class BookAppointment : AppCompatActivity(), OnDateSetListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        setListeners()
    }

    private fun setListeners() {
        dateBtn.setOnClickListener {
            val datePicker: DialogFragment = DatePickerFragment()
            datePicker.show(supportFragmentManager, "date picker")
        }
    }

    override fun onDateSet(
        view: DatePicker,
        year: Int,
        month: Int,
        dayOfMonth: Int
    ) {
        val c = Calendar.getInstance()
        c[Calendar.YEAR] = year
        c[Calendar.MONTH] = month
        c[Calendar.DAY_OF_MONTH] = dayOfMonth
        val sDate =
            DateFormat.getDateInstance(DateFormat.FULL).format(c.time)
        date.text = sDate
    }
}