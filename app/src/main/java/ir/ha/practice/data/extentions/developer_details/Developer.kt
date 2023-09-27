package ir.ha.practice.data.extentions.developer_details

import ir.ha.practice.data.remote_data.DeveloperDetailsRemoteResponse
import ir.ha.practice.data.entities.DeveloperDetailsEntity

fun DeveloperDetailsRemoteResponse.toDeveloperDetailsEntity() : DeveloperDetailsEntity {
    return DeveloperDetailsEntity(
        id = this.id,
        firstName = this.firstName,
        lastName = this.lastName ,
        birthDate = this.birthDate ,
        jobTitle = this.jobTitle ,
        profileImage = this.profileImage ,
        contactInfo = this.contactInfo,
        skills = this.skills,
        resume = this.resume
    )
}