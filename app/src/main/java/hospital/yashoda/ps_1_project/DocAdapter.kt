package hospital.yashoda.ps_1_project

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class DocAdapter(doctorList: ArrayList<DoctorItem>) :
    RecyclerView.Adapter<DocAdapter.DocViewHolder>() {
    private val mDocList: ArrayList<DoctorItem>

    class DocViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var mImageView: ImageView
        var mDocName: TextView
        var mDocInfo: TextView

        init {
            mImageView = itemView.findViewById(R.id.docimage)
            mDocName = itemView.findViewById(R.id.docname)
            mDocInfo = itemView.findViewById(R.id.docinfo)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DocViewHolder {
        val v: View =
            LayoutInflater.from(parent.context).inflate(R.layout.doctoritem, parent, false)
        return DocViewHolder(v)
    }

    override fun onBindViewHolder(
        holder: DocViewHolder,
        position: Int
    ) {
        val currentItem: DoctorItem = mDocList[position]
        holder.mImageView.setImageResource(currentItem.imageResource)
        holder.mDocName.text = currentItem.docName
        holder.mDocInfo.text = currentItem.docInfo
    }

    override fun getItemCount(): Int {
        return mDocList.size
    }

    init {
        mDocList = doctorList
    }
}
