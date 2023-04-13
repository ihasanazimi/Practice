package ir.ha.practice.model

data class Organize(
    val finishedPositionDate: String,
    val organizeName: String,
    val organizeLogo: String,
    val positionTitle: String,
    val positionType: String,
    val projects: List<Project>,
    val startedPositionDate: String
) {

    fun getDistanceOfTowPosition() = "$startedPositionDate  -  $finishedPositionDate"
    fun getPositionByType() = "$positionTitle  |  $positionType"

}