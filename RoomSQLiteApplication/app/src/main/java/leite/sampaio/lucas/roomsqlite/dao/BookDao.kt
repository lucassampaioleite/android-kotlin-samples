package leite.sampaio.lucas.roomsqlite.dao

import androidx.room3.Dao
import androidx.room3.Delete
import androidx.room3.Insert
import androidx.room3.Query
import androidx.room3.Update
import kotlinx.coroutines.flow.Flow
import leite.sampaio.lucas.roomsqlite.entities.Book

@Dao
interface BookDao {

    @Insert
    suspend fun insert(book: Book)

    @Update
    suspend fun update(book: Book)

    @Delete
    suspend fun delete(book: Book)

    @Query("SELECT * FROM books ORDER BY title")
    fun listAll(): Flow<List<Book>>

    @Query("SELECT * FROM books WHERE id = :id")
    suspend fun findById(id: Int): Book?
}


