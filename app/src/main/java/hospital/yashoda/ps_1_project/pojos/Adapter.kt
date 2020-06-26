package hospital.yashoda.ps_1_project.pojos

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hospital.yashoda.ps_1_project.R
import kotlinx.android.synthetic.main.contact_list.view.*
import kotlinx.android.synthetic.main.contact_list.view.share

class Adapter(private val arrayList: ArrayList<Model>, val context: Context) :
   RecyclerView.Adapter<Adapter.ViewHolder>() {

             class ViewHolder(itemView: View, val context: Context) : RecyclerView.ViewHolder(itemView) {

                 init {

                     itemView.share.setOnClickListener() {

                         val message: String = "Hospital"

                         val intent = Intent()
                         intent.action = Intent.ACTION_SEND
                         intent.putExtra(Intent.EXTRA_TEXT, itemView.ABC.text.toString() + "\n" + itemView.addressText.text.toString() + "\n" + itemView.callText.text.toString() + "\n" + itemView.mailText.text.toString())
                         intent.type = "text/plain"

                         context.startActivity(Intent.createChooser(intent, "Please select app: "))
                     }

                         itemView.website.setOnClickListener() {

                             val intent = Intent()
                             intent.action = Intent.ACTION_VIEW
                             intent.data = Uri.parse("https://www.yashodahospitals.com/")
                             context.startActivity(intent)

                         }



                 }

                 fun bindItems(model: Model) {
                     itemView.ABC.text = model.ABC
                     itemView.addressText.text = model.address
                     itemView.callText.text = model.call
                     itemView.mailText.text = model.mail
                 }

             }

             override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
                 val v = LayoutInflater.from(parent.context)
                     .inflate(R.layout.contact_list, parent, false)

                 return ViewHolder(v, context)
             }

             override fun getItemCount(): Int {
                 return arrayList.size
             }

             override fun onBindViewHolder(holder: ViewHolder, position: Int) {

                 holder.bindItems(arrayList[position])
             }

}




