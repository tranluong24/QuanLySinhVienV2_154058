package android.app.quanlysinhvienv2

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment

class DialogStudent : DialogFragment() {

    private lateinit var edtName: EditText
    private lateinit var edtMSSV: EditText
    private lateinit var btnSave: Button

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = requireActivity().layoutInflater
        val view = inflater.inflate(R.layout.dialog_student_layout, null)

        edtName = view.findViewById(R.id.edtName)
        edtMSSV = view.findViewById(R.id.edtMSSV)
        btnSave = view.findViewById(R.id.save)

        val builder = AlertDialog.Builder(requireActivity())
        builder.setView(view)

        btnSave.setOnClickListener {
            val name = edtName.text.toString().trim()
            val mssv = edtMSSV.text.toString().trim()

            if (name.isNotEmpty() && mssv.isNotEmpty()) {
                // Gọi hàm thêm sinh viên vào danh sách từ Activity chính
//                (activity as? MainActivity)?.addStudent(name, mssv)
                dismiss() // Đóng dialog sau khi thêm sinh viên
            } else {
                Toast.makeText(context, "Please enter valid information", Toast.LENGTH_SHORT).show()
            }
        }

        return builder.create()
    }
}