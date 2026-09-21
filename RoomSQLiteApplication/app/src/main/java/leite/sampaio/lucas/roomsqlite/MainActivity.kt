package leite.sampaio.lucas.roomsqlite

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.launch
import leite.sampaio.lucas.roomsqlite.adapters.BookAdapter
import leite.sampaio.lucas.roomsqlite.dao.BookDao
import leite.sampaio.lucas.roomsqlite.databinding.ActivityMainBinding
import leite.sampaio.lucas.roomsqlite.db.AppDatabase
import leite.sampaio.lucas.roomsqlite.entities.Book


class MainActivity : ComponentActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var database: AppDatabase
    private lateinit var bookDao: BookDao
    private lateinit var adapter: BookAdapter

    private var selectedBook: Book? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = AppDatabase.getDatabase(this)
        bookDao = database.bookDao()

        configureRecyclerView()
        configureButtons()
        observeBooks()
    }

    private fun configureRecyclerView() {
        adapter = BookAdapter(
            onItemClick = { book -> selectBook(book) },
            onDeleteClick = { book -> deleteBook(book) }
        )
        binding.rvBooks.layoutManager = LinearLayoutManager(this)

        binding.rvBooks.adapter = adapter
    }

    private fun configureButtons() {
        binding.btnSave.setOnClickListener {
            insertBook()
        }

        binding.btnUpdate.setOnClickListener {
            updateBook()
        }
    }

    private fun insertBook() {
        val title = binding.etTitle.text.toString()
        val author = binding.etAuthor.text.toString()
        val year = binding.etYear.text.toString()

        if (title.isBlank() || author.isBlank() || year.isBlank()) {
            Toast.makeText(this,"Preencha todos os campos.",Toast.LENGTH_SHORT).show()
            return
        }

        val book = Book(title = title, author = author, year = year.toInt())

        lifecycleScope.launch {
            bookDao.insert(book)
            clearForm()
            Toast.makeText(this@MainActivity,"Livro inserido.",Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateBook() {
        val book = selectedBook

        if (book == null) {
            Toast.makeText(this, "Selecione um livro.", Toast.LENGTH_SHORT).show()
            return
        }

        val title = binding.etTitle.text.toString()
        val author = binding.etAuthor.text.toString()
        val year = binding.etYear.text.toString()

        if (title.isBlank() || author.isBlank() || year.isBlank() ) {
            Toast.makeText(this, "Preencha todos os campos.", Toast.LENGTH_SHORT).show()
            return
        }

        val updatedBook = book.copy(title = title, author = author, year = year.toInt())

        lifecycleScope.launch {
            bookDao.update(updatedBook)
            selectedBook = null
            clearForm()
            Toast.makeText(this@MainActivity,"Livro atualizado.",Toast.LENGTH_SHORT).show()
        }
    }

    private fun clearForm() {
        binding.etTitle.text.clear()
        binding.etAuthor.text.clear()
        binding.etYear.text.clear()

        selectedBook = null
    }

    private fun deleteBook(book: Book) {
        lifecycleScope.launch {
            bookDao.delete(book)

            if (selectedBook?.id == book.id) {
                selectedBook = null
                clearForm()
            }

            Toast.makeText(this@MainActivity,"Livro excluído.",Toast.LENGTH_SHORT).show()
        }
    }


    private fun selectBook(book: Book) {
        selectedBook = book

        binding.etTitle.setText(book.title)
        binding.etAuthor.setText(book.author)
        binding.etYear.setText(book.year.toString())

        Toast.makeText(this,"Livro selecionado.",Toast.LENGTH_SHORT).show()
    }

    private fun observeBooks() {
        lifecycleScope.launch {
           bookDao.listAll().collect { books -> adapter.updateList(books) }
        }
    }
}

