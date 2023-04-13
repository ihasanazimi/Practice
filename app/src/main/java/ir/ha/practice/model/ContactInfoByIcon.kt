package ir.ha.practice.model

class ContactInfoByIcon(val addressId: String, val addressIcon: Int, val typeEnum: ContactInfoEnum)

enum class ContactInfoEnum {
    call , email , linkedin , telegram
}