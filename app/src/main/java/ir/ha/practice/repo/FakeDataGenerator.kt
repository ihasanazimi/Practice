package ir.ha.practice.repo

import ir.ha.practice.model.FakePojo

object FakeDataGenerator {

    fun getDataFakeGenerator() : List<FakePojo>{
        val arr = arrayListOf<FakePojo>()
        val imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/3/3e/Android_logo_2019.png/800px-Android_logo_2019.png"
        if (arr.isEmpty()){
            for (i in 0..20){
                arr.add(FakePojo("$i" , imageUrl))
            }
        }
        return arr
    }
}