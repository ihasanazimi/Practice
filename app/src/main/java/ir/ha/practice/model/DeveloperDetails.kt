package ir.ha.practice.model

data class DeveloperDetails(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val birthDate: String,
    val jobTitle: String,
    val profileImage: String,
    val contactInfo: ContactInfo,
    val skills: List<String>,
    val resume: Resume
) : java.io.Serializable {

    fun getFullName() = "$firstName $lastName"
    fun getJobInOrganization() = "$jobTitle in ${resume.organizes[0].organizeName}"
}