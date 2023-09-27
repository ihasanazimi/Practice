package ir.ha.practice.model.extentions.developer_details

import ir.ha.practice.model.data.DeveloperDetailsRemoteResponse
import ir.ha.practice.model.entities.DeveloperDetailsEntity

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