package ir.ha.practice.utility

import android.graphics.Bitmap
import com.bumptech.glide.Glide
import ir.ha.practice.ApplicationLoader
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream

@DelicateCoroutinesApi
fun saveUserCoverImage(userCover : String, saveDir : String){
    // save profile cover into storage and save it on prefFile
    if (userCover.isNotEmpty() && saveDir.isNotEmpty()) {
        GlobalScope.launch(Dispatchers.IO) {
            saveImage(
                Glide.with(ApplicationLoader.context!!)
                    .asBitmap()
                    .load(userCover)
                    .placeholder(android.R.drawable.progress_indeterminate_horizontal)
                    .error(android.R.drawable.stat_notify_error)
                    .submit()
                    .get(), saveDir
            )
        }
    }
}


private fun saveImage(image: Bitmap, saveDir : String) {
    var savedImagePath: String? = null
    val imageFileName = "JPEG_" + "RVQ_USER_COVER_FILE" + ".jpg"
    val storageDir = File(saveDir , "userCoverDir")
    var success = true
    if (!storageDir.exists()) success = storageDir.mkdirs()
    if (success) {
        val imageFile = File(storageDir, imageFileName)
        savedImagePath = imageFile.absolutePath
        try {
            val fOut: OutputStream = FileOutputStream(imageFile)
            image.compress(Bitmap.CompressFormat.JPEG, 100, fOut)
            fOut.close()
        } catch (e: Exception) { e.printStackTrace() }
//        KtPrefs.userCoverFilePath = savedImagePath
    }
}
