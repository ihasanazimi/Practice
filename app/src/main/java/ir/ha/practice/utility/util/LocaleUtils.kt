package ir.ha.practice.utility.util

import android.content.Context
import java.util.*

const val PERSIAN_LANGUAGE_CODE = "fa"
const val PERSIAN_COUNTRY_CODE = "IR"
const val ENGLISH_LANGUAGE_CODE = "en"
const val ENGLISH_COUNTRY_CODE = "US"
const val LOCALE_INIT = "locale_init"
const val BUBBLE_CARD_GUID = "card_guid_bubble"
const val SHOW_CARD_GUID_LAYOUT = "show_guid_card_layout"

fun localizedContext(baseContext: Context, locale: Locale = Locale(ENGLISH_LANGUAGE_CODE)): Context {
    Locale.setDefault(locale)
    val configuration = baseContext.resources.configuration
    configuration.setLocale(locale)
    configuration.setLayoutDirection(locale)
    return baseContext.createConfigurationContext(configuration)
}
