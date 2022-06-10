package ir.ha.dep.model

object FakeDataGenerator {

    fun getDataFakeGenerator() : List<FakeDataModel>{
        val arr = arrayListOf<FakeDataModel>()
        val imageUrl = "https://post.medicalnewstoday.com/wp-content/uploads/sites/3/2020/02/326012_1100-800x825.jpg"
        if (arr.isEmpty()){
            for (i in 0..20){
                arr.add(FakeDataModel("$i" , imageUrl))
            }
        }
        return arr
    }
}