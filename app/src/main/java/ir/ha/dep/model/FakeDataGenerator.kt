package ir.ha.dep.model

import java.util.*

fun getFakeDataForSlider() : List<Banner>{
    val arrayList : ArrayList<Banner> = arrayListOf()
    for (i in 0..5){
        arrayList.add(Banner("https://styles.redditmedia.com/t5_2rw0n/styles/communityIcon_098dz881tgk81.png","$i"+"بنر شاره "))
    }
    return arrayList

}