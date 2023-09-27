package ir.ha.practice.data.entities

import android.util.Log
import java.io.Serializable

data class DeveloperDetailsEntity (
    val id: Int,
    val firstName: String,
    val lastName: String,
    val birthDate: String,
    val jobTitle: String,
    val profileImage: String,
    val contactInfo: ContactInfoEntity,
    val skills: List<String>,
    val resume: ResumeEntity
) : Serializable{
    fun getFullName() = "$firstName $lastName"
    fun getJobInOrganization() : String{
        return  ("$jobTitle in ${resume.organizes[0].organizeName}").also {
            Log.i("getJobInOrganization", "getJobInOrganization -  ${"$jobTitle in ${resume.organizes[0].organizeName}"}")
        }
    }
}