package android.app.quanlysinhvienv2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/*
*
* - Option Menu - App bar
- Fragment - OK
- Context menu
+ Floating context menu
a. Ddawng ky view
b. onCreateContextMenu
c. onContextItemSelected
+ Contexture action mode

- Navigation
* */

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

        //ket noi toolbar
        val toolbar: Toolbar = findViewById(R.id.toolBar)
        setSupportActionBar(toolbar)

        //Gan LayoutManager vao recycleView
        val recycleView: RecyclerView = findViewById(R.id.RecycleView)
        recycleView.layoutManager = LinearLayoutManager(this)

        //Gan Adapter
        adapter = StudentAdapter(students)
        recycleView.adapter = adapter

        //Dang ki view cho Context menu
        registerForContextMenu(recycleView)


//        val button = findViewById<Button>(R.id.btnAdd)

//        button.setOnClickListener(){
//            val dialog = DialogStudent()
//            dialog.show(supportFragmentManager, "AddStudentDialog")
//        }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        if(v is RecyclerView)
            menuInflater.inflate(R.menu.context_menu, menu) // Nạp menu từ XML
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
//        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        val pos = (adapter).getSelectedPosition()
        return when (item.itemId) {
            R.id.editStudent -> {
                val fragment = StudentFragment.newInstance(pos, students[pos].getName(), students[pos].getMSSV())
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, fragment)
                    .addToBackStack(null)
                    .commit()
                return true
            }

            R.id.delete -> {
                removeStudent(pos)
                return true
            }

            else -> super.onContextItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.option_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.addStudent -> {
                //Mo fragment Add student
                val studentFragment = StudentFragment()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, studentFragment)
                    .addToBackStack(null)
                    .commit()
                true
            }

            R.id.setting -> {
                Toast.makeText(this, "not found 404 !", Toast.LENGTH_SHORT).show()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    fun addStudent(name: String, mssv: String) {
        val newStudent =
            StudentModel(name, mssv) // Bạn có thể tạo lớp Student để chứa thông tin sinh viên
        students.add(newStudent)
        // Cập nhật UI để hiển thị danh sách sinh viên
        adapter.notifyDataSetChanged()
    }

    fun editStudent(index: Int, name: String, mssv: String) {
        students[index].setMSSV(mssv)
        students[index].setName(name)
        // Cập nhật UI để hiển thị danh sách sinh viên
        adapter.notifyDataSetChanged()
    }

    fun removeStudent(index: Int) {
        // Cập nhật UI để hiển thị danh sách sinh viên
        students.removeAt(index)
        adapter.notifyDataSetChanged()
    }
}