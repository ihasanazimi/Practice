package ir.ha.practice.model.entities

data class OrganizeEntity(
    val finishedPositionDate: String,
    val organizeName: String,
    val organizeLogo: String,
    val positionTitle: String,
    val positionType: String,
    val projects: List<ProjectEntity>,
    val startedPositionDate: String
) {

    fun getDistanceOfTowPosition() = "$startedPositionDate  -  $finishedPositionDate"
    fun getPositionByType() = "$positionTitle  |  $positionType"

}