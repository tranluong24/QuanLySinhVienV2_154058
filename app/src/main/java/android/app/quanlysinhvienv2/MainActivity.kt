package android.app.quanlysinhvienv2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    //Mutable - Co the thay doi danh sach
    val students = mutableListOf(
        StudentModel("Tran Dac Luong", "20215611"),
        StudentModel("Trần Thị Bảo", "SV002"),
        StudentModel("Lê Hoàng Cường", "SV003"),
        StudentModel("Phạm Thị Dung", "SV004"),
        StudentModel("Đỗ Minh Đức", "SV005"),
        StudentModel("Vũ Thị Hoa", "SV006"),
        StudentModel("Hoàng Văn Hải", "SV007"),
        StudentModel("Bùi Thị Hạnh", "SV008"),
        StudentModel("Đinh Văn Hùng", "SV009"),
        StudentModel("Nguyễn Thị Linh", "SV010"),
        StudentModel("Phạm Văn Long", "SV011"),
        StudentModel("Trần Thị Mai", "SV012"),
        StudentModel("Lê Thị Ngọc", "SV013"),
        StudentModel("Vũ Văn Nam", "SV014"),
        StudentModel("Hoàng Thị Phương", "SV015"),
        StudentModel("Đỗ Văn Quân", "SV016"),
        StudentModel("Nguyễn Thị Thu", "SV017"),
        StudentModel("Trần Văn Tài", "SV018"),
        StudentModel("Phạm Thị Tuyết", "SV019"),
        StudentModel("Lê Văn Vũ", "SV020")
    )

    private lateinit var adapter: StudentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //Gan LayoutManager vao recycleView
        val recycleView: RecyclerView = findViewById(R.id.RecycleView)
        recycleView.layoutManager = LinearLayoutManager(this)

        //Gan Adapter
        adapter = StudentAdapter(students)
        recycleView.adapter = adapter

        val button = findViewById<Button>(R.id.btnAdd)

        button.setOnClickListener(){
            val dialog = DialogStudent()
            dialog.show(supportFragmentManager, "AddStudentDialog")
        }
    }

    fun addStudent(name: String, mssv: String) {
        val newStudent = StudentModel(name, mssv) // Bạn có thể tạo lớp Student để chứa thông tin sinh viên
        students.add(newStudent)
        // Cập nhật UI để hiển thị danh sách sinh viên
        adapter.notifyDataSetChanged()
    }
}