package leite.sampaio.lucas.roomsqlite.entities

import androidx.room3.Entity
import androidx.room3.PrimaryKey

@Entity(tableName = "books")
data class Book(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val author: String,
    val year: Int
)
