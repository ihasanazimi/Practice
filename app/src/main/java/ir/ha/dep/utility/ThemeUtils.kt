package ir.ha.dep.utility

import androidx.appcompat.app.AppCompatDelegate

object ThemeUtils {

    fun changeTheme(isDarkTheme: Boolean) {
        if (isDarkTheme)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        else AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}
