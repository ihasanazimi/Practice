package ir.ha.practice.data.remote_data

import com.google.gson.annotations.SerializedName
import ir.ha.practice.data.entities.ContactInfoEntity
import ir.ha.practice.data.entities.ResumeEntity

data class DeveloperDetailsRemoteResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("firstName")
    val firstName: String,
    @SerializedName("lastName")
    val lastName: String,
    @SerializedName("birthDate")
    val birthDate: String,
    @SerializedName("jobTitle")
    val jobTitle: String,
    @SerializedName("profileImage")
    val profileImage: String,
    @SerializedName("contactInfo")
    val contactInfo: ContactInfoEntity,
    @SerializedName("skills")
    val skills: List<String>,
    @SerializedName("resume")
    val resume: ResumeEntity
)