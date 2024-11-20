package android.app.quanlysinhvienv2

data class StudentModel(
    private val name: String,
    private val mssv: String
){
    fun getMSSV(): String = mssv
    fun getName(): String = name
}
