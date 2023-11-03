package leite.sampaio.lucas.bookmanagerapplicationwithsqlite

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

class DatabaseController(context: Context) {

    private val database: CreateDB
    private val writableDatabase: SQLiteDatabase
    private val readableDatabase: SQLiteDatabase

    init {
        database = CreateDB(context)
        writableDatabase = database.writableDatabase
        readableDatabase = database.readableDatabase
    }

    fun insertData(title: String, author: String, publisher: String): String {
        val values = ContentValues()
        values.put(CreateDB.TITLE, title)
        values.put(CreateDB.AUTHOR, author)
        values.put(CreateDB.PUBLISHER, publisher)
        val result = writableDatabase.insert(CreateDB.TABLE, null, values)
        return if (result == -1L) "Error inserting record" else "Record inserted successfully"
    }

    fun loadData(): Cursor? {
        val fields = arrayOf(CreateDB.ID, CreateDB.TITLE)
        val cursor = readableDatabase.query(CreateDB.TABLE, fields, null, null,
            null, null, null, null)
        cursor.moveToFirst()
        return cursor
    }

    fun loadDataById(id: Int): Cursor? {
        val fields = arrayOf(CreateDB.ID, CreateDB.TITLE, CreateDB.AUTHOR, CreateDB.PUBLISHER)
        val where = "${CreateDB.ID} = ?"
        val whereArgs = arrayOf(id.toString())
        val cursor = readableDatabase.query(CreateDB.TABLE, fields, where, whereArgs, null,
            null, null, null)
        cursor.moveToFirst()
        return cursor
    }

    fun updateData(id: Int, title: String, author: String, publisher: String) {
        val values = ContentValues()
        values.put(CreateDB.TITLE, title)
        values.put(CreateDB.AUTHOR, author)
        values.put(CreateDB.PUBLISHER, publisher)
        val where = "${CreateDB.ID} = ?"
        val whereArgs = arrayOf(id.toString())
        writableDatabase.update(CreateDB.TABLE, values, where, whereArgs)
    }

    fun deleteData(id: Int) {
        val where = "${CreateDB.ID} = ?"
        val whereArgs = arrayOf(id.toString())
        writableDatabase.delete(CreateDB.TABLE, where, whereArgs)
    }
}