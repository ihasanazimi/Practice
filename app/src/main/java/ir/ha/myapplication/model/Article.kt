package ir.ha.myapplication.model

data class Article(
    val category: List<String>,
    val description: String,
    val id: Int,
    val imageUrl: String,
    val seen: Int,
    val title: String
)