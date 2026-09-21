package leite.sampaio.lucas.roomsqlite.db

import android.content.Context
import androidx.room3.Database
import androidx.room3.Room
import androidx.room3.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import leite.sampaio.lucas.roomsqlite.dao.BookDao
import leite.sampaio.lucas.roomsqlite.entities.Book

@Database(entities = [Book::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun bookDao(): BookDao
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room
                    .databaseBuilder<AppDatabase>(context.applicationContext,"books.db")
                    .setDriver(BundledSQLiteDriver())
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}