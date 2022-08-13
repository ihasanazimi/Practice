package ir.ha.dep.repo

import ir.ha.dep.model.FakeDataModel

object FakeDataGenerator {

    fun getDataFakeGenerator() : List<FakeDataModel>{
        val arr = arrayListOf<FakeDataModel>()
        val imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/3/3e/Android_logo_2019.png/800px-Android_logo_2019.png"
        if (arr.isEmpty()){
            for (i in 0..20){
                arr.add(FakeDataModel("$i" , imageUrl))
            }
        }
        return arr
    }
}