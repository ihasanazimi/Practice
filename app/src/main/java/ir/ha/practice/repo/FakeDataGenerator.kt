package ir.ha.practice.repo

import ir.ha.practice.model.FakePojo

object FakeDataGenerator {

    fun getDataFakeGenerator() : List<FakePojo>{
        val arr = arrayListOf<FakePojo>()
        val imageUrl = "https://www.pngplay.com/wp-content/uploads/2/Android-Logo-Download-Free-PNG.png"
        if (arr.isEmpty()){
            for (i in 0..20){
                arr.add(FakePojo("$i" , imageUrl))
            }
        }
        return arr
    }
}