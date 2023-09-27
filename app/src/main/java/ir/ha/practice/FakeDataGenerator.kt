package ir.ha.practice

import ir.ha.practice.model.entities.FakeEntity

object FakeDataGenerator {

    fun getDataFakeGenerator() : List<FakeEntity>{
        val arr = arrayListOf<FakeEntity>()
        val imageUrl = "https://www.pngplay.com/wp-content/uploads/2/Android-Logo-Download-Free-PNG.png"
        if (arr.isEmpty()){
            for (i in 0..20){
                arr.add(FakeEntity("$i" , imageUrl))
            }
        }
        return arr
    }
}