package ir.ha.practice.model.entities

import ir.ha.practice.model.enums.ContactInfoEnum

class ContactInfoByIconEntity(
    val addressId: String,
    val addressIcon: Int,
    val typeEnum: ContactInfoEnum
)