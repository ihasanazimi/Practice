package ir.ha.practice.model

data class Organize(
    val finishedPositionDate: String,
    val organizeName: String,
    val positionTitle: String,
    val positionType: String,
    val projects: List<Project>,
    val startedPositionDate: String
)