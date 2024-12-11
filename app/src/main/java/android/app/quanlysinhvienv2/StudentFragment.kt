package android.app.quanlysinhvienv2

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment

class StudentFragment : Fragment() {
    private var studentId: Int = -1
    private var studentName: String = ""
    private var studentMSSV: String = ""
    //Factory Methor Parttern
    /*
    * Cung cấp phương thức khởi tạo ngay trong class
    * Khi nào cần thì chỉ cần goi phuong thuc do la duoc
    * -> Khong can lap lai nhieu lan doan code khoi tao doi tuong
    * Khi can sửa chỉ cần sửa trong phương thức khởi tạo
    * */

    //Giong static object - de su dung ma k can tao doi tuong
    companion object {
        fun newInstance(num: Int, name: String, mssv: String): StudentFragment {
            val fragment = StudentFragment()
            val args = Bundle()
            //Serializable - chuyen doi tuong thanh chuoi byte de truyen
            // -> bat buoc khi truyen doi tuong di
            args.putInt("student_index", num)
            args.putString("student_name", name)
            args.putString("student_mssv", mssv)
            fragment.arguments = args
            return fragment
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        arguments?.let {
//            student = StudentModel("hel","13134")
//        }
        studentId = arguments?.getInt("student_index") ?: studentId
        studentName = arguments?.getString("student_name") ?: studentName
        studentMSSV = arguments?.getString("student_mssv") ?: studentMSSV
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.student_fragment_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Gan cac thanh phan trong fragment de xu ly
        val nameEdt = view.findViewById<EditText>(R.id.nameEdt)
        val mssvEdt = view.findViewById<EditText>(R.id.mssvEdt)
        val saveBtn = view.findViewById<Button>(R.id.saveBtn)
        val cancelBtn = view.findViewById<Button>(R.id.cancelBtn)

        if(studentId > 0){
            nameEdt.setText(studentName)
            mssvEdt.setText(studentMSSV)
        }

        saveBtn.setOnClickListener {
            //Save or Update
            if (mssvEdt.text.toString() == "" || mssvEdt.text.toString() == "") {
                Toast.makeText(
                    activity as? MainActivity,
                    "Dien day du thong tin !",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                if (studentId < 0) {
                    (activity as? MainActivity)?.addStudent(
                        nameEdt.text.toString(),
                        mssvEdt.text.toString()
                    )
                } else {
                    (activity as? MainActivity)?.editStudent(studentId,
                        nameEdt.text.toString(),
                        mssvEdt.text.toString())
                }
                requireActivity().supportFragmentManager.popBackStack()
            }
        }

        cancelBtn.setOnClickListener {
            //Back to home
            requireActivity().supportFragmentManager.popBackStack()
        }

    }
}