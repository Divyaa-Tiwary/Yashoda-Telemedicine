package hospital.yashoda.ps_1_project

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.MimeTypeMap
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.fragment_doctor_bills2.*


class DoctorBills2 : Fragment() {

    private lateinit var id: String
    private var image_url: Uri = Uri.EMPTY
    private var imageBitmap = Uri.EMPTY
    private var storageReference = FirebaseStorage.getInstance().reference
    private var image = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            id = it.getString("id")!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_doctor_bills2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        fetchData()
        setListeners()
    }

    private fun setListeners() {
        uploadButton.setOnClickListener {

            openFilePicker(12)
        }
    }

    private fun openFilePicker(request: Int) {
        val intent: Intent = Intent().apply {
            type = "image/*"
            action = Intent.ACTION_GET_CONTENT
        }
        startActivityForResult(intent, request)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 12 && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                image_url = data?.data!!
                Glide.with(this).load(image_url).into(reportimage)
                uploadFile()
            }
        }
    }

    private fun uploadFile() {

        if (Uri.EMPTY != image_url) {
            val mStorageReference = storageReference.child("bills").child(
                System.currentTimeMillis().toString() + "." + getFileExtension(image_url)
            )
            var uploadTask = mStorageReference.putFile(image_url)
            val urlTask = uploadTask.continueWithTask { task ->
                if (!task.isSuccessful) {
                    task.exception?.let {
                        throw it
                    }
                }
                mStorageReference.downloadUrl
            }.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    imageBitmap = task.result
                    uploadToDatabase()
                } else {

                }
            }

        } else {

        }
    }

    private fun getFileExtension(uri: Uri): String? {
        val cr = context?.contentResolver
        val mimeTypeMap = MimeTypeMap.getSingleton()
        return mimeTypeMap.getExtensionFromMimeType(cr?.getType(uri))
    }

    private fun uploadToDatabase() {
        val firestore = FirebaseFirestore.getInstance()
        firestore.collection("Users").document(id).update("bills", imageBitmap.toString())
            .addOnSuccessListener {
                Toast.makeText(requireContext(), "Uploaded image", Toast.LENGTH_SHORT).show()
            }
    }


    private fun fetchData() {
        val firestore = FirebaseFirestore.getInstance()
        firestore.collection("Users").document(id).get().addOnSuccessListener {
            pname.text = it["name"].toString()
            page.text = it["age"].toString()
            pgen.text = it["gender"].toString()
            pno.text = it["phoneNumber"].toString()
            pmail.text = it["email"].toString()
            pstate.text = it["state"].toString()
            if (it["medicalReport"]?.toString().equals("").not()) {
                image = it["medicalReport"].toString()
                Glide.with(this).load(it["medicalReport"].toString()).into(reportimage)
                uploadButton.text = "Update image"
            }
        }
    }


    companion object {

        fun newInstance(id: String) = DoctorBills2().apply {
            arguments = Bundle().apply {
                putString("id", id)
            }
        }
    }
}