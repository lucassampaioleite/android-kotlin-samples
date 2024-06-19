package leite.sampaio.lucas.bookmanagerapplicationwithsqlite.sqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class CreateDB(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    companion object {
        const val DB_NAME = "book.db"
        const val TABLE = "book"
        const val ID = "_id"
        const val TITLE = "title"
        const val AUTHOR = "author"
        const val PUBLISHER = "publisher"
        const val DB_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase) {
        val sql = String.format(
            "CREATE TABLE %s (%s integer primary key autoincrement, %s text, %s text, %s text)",
            TABLE, ID, TITLE, AUTHOR, PUBLISHER
        )
        db.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE")
        onCreate(db)
    }
}



