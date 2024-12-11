package android.app.quanlysinhvienv2

import java.io.Serializable

class StudentModel(
    private var name: String,
    private var mssv: String
) : Serializable
{
    fun getMSSV(): String = mssv
    fun getName(): String = name

    fun setMSSV(mssv: String){
        this.mssv =mssv
    }
    fun setName(name: String){
        this.name =name
    }
}
