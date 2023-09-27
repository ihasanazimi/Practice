package ir.ha.practice.data.entities

import ir.ha.practice.data.enums.ContactInfoEnum

class ContactInfoByIconEntity(
    val addressId: String,
    val addressIcon: Int,
    val typeEnum: ContactInfoEnum
)