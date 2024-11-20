package android.app.quanlysinhvienv2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StudentAdapter(private val students: List<StudentModel>)
    : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>(){
        class StudentViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
            val nameText: TextView = itemView.findViewById(R.id.nameText)
            val mssvText: TextView = itemView.findViewById(R.id.mssvText)
        }
    //tao ra viewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout, parent, false)
        return StudentViewHolder(view)
    }
    //Khai bao so luong item de recycleView co the tinh toan so luong hien thi tren 1 khung hinh
    override fun getItemCount(): Int = students.size

    //Khi can su dung lai viewHolder (khi keo danh sach xuong )
    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = students[position]
        holder.nameText.text = student.getName()
        holder.mssvText.text = student.getMSSV()
    }
}